package app.web.persistence.mappers;

import app.web.constants.attributes.WebGlobalAttributes;
import app.web.entities.AccountInfo;
import app.web.entities.FullHistory;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;

import java.util.*;

public class FullHistoryMapperImpl implements FullHistoryMapper
{
    
    AccountInfoMapper accountInfoMapper;
    OrderMapper orderMapper;
    
    public FullHistoryMapperImpl( AccountInfoMapper accountInfoMapper, OrderMapper orderMapper )
    {
        this.accountInfoMapper = accountInfoMapper;
        this.orderMapper = orderMapper;
    }
    
    @Override
    public List< FullHistory > readAllFull() throws DatabaseException
    {
        List< FullHistory > fullHistoryList = new ArrayList<>();
        
        Map< Integer, AccountInfo > accountInfoMap = this.accountInfoMapper.readAll();
        
        FullHistory fullHistory;
        Set< Integer > usersWithAccountInfo = new TreeSet<>();
        Integer userId;
        
        for ( AccountInfo accountInfo : accountInfoMap.values() ) {
            fullHistory = new FullHistory();
            fullHistory.setAccountInfo( accountInfo );
            
            fullHistory.setOrders( this.orderMapper.readAllByAccountInfoIdFull( accountInfo ) );
            
            userId = accountInfo.getUserId();
            fullHistory.setUser( WebGlobalAttributes.USER_MAP.get( userId ) );
            usersWithAccountInfo.add( userId );
            
            fullHistoryList.add( fullHistory );
        }
        
        for ( User user : WebGlobalAttributes.USER_MAP.values() ) {
            if ( !usersWithAccountInfo.contains( user.getUserId() ) ) {
                fullHistory = new FullHistory();
                
                fullHistory.setOrders( null );
                fullHistory.setAccountInfo( null );
                
                fullHistory.setUser( user );
                
                fullHistoryList.add( fullHistory );
            }
        }
        
        return fullHistoryList;
    }
    
}
