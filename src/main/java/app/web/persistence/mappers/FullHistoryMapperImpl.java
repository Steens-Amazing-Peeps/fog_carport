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
    public Map< Integer, FullHistory > readAllFull() throws DatabaseException
    {
        Map< Integer, FullHistory > fullHistoryMap = new LinkedHashMap<>();
        
        Map< Integer, AccountInfo > accountInfoMap = this.accountInfoMapper.readAll();


//        Set< Integer > usersWithAccountInfo = new TreeSet<>();
        Set< AccountInfo > seenAccountInfo = new TreeSet<>();
        
        FullHistory fullHistory;
        Integer userId;
        for ( AccountInfo accountInfo : accountInfoMap.values() ) {
            
            if ( !seenAccountInfo.contains( accountInfo ) ) {
                seenAccountInfo.add( accountInfo );
                
                userId = accountInfo.getUserId();
                
                if ( fullHistoryMap.containsKey( userId ) ) {
                    fullHistory = fullHistoryMap.get( userId );
                } else {
                    fullHistory = new FullHistory();
                    fullHistory.setUser( WebGlobalAttributes.USER_MAP.get( userId ) );
                }
                
                fullHistory.addAccountInfo( accountInfo );
                fullHistory.addOrderMapWithAccountInfo( this.orderMapper.readAllByAccountInfoIdFull( accountInfo ) );

//                usersWithAccountInfo.add( userId );
                
                fullHistoryMap.put( userId, fullHistory );
            }
        }
        
        Set< Integer > usersWithoutContactInfo = new LinkedHashSet<>( WebGlobalAttributes.USER_MAP.keySet() );
        usersWithoutContactInfo.removeAll( fullHistoryMap.keySet() );
        
        for ( Integer id : usersWithoutContactInfo ) {
            fullHistory = new FullHistory();
            
            fullHistory.setUser( WebGlobalAttributes.USER_MAP.get( id ) );
            
            fullHistoryMap.put( id, fullHistory );
        }
        
        return fullHistoryMap;
    }
    
}
