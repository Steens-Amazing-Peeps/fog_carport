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
        
        
        FullHistory fullHistory;
        Integer userId;
        for ( AccountInfo accountInfo : accountInfoMap.values() ) {
            
            userId = accountInfo.getUserId();
            if ( !fullHistoryMap.containsKey( userId ) ) {
                fullHistory = new FullHistory();
                fullHistory.setUser( WebGlobalAttributes.USER_MAP.get( userId ) );
            } else {
                fullHistory = fullHistoryMap.get( userId );
            }
            
            fullHistory.addOrderMapWithAccountInfo( this.orderMapper.readAllByAccountInfoIdFull( accountInfo ) );
            
            fullHistoryMap.put( userId, fullHistory );
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
    
    @Override
    public FullHistory readSingle( Integer userId ) throws DatabaseException
    {
        User currentUser = WebGlobalAttributes.USER_MAP.get( userId );
        return this.readSingle( currentUser );
    }
    
    @Override
    public FullHistory readSingle( User currentUser ) throws DatabaseException
    {
        FullHistory fullHistory = new FullHistory();
        fullHistory.setUser( currentUser );
        
        Map< Integer, AccountInfo > accountInfoMap = this.accountInfoMapper.readAllByUserId( currentUser.getUserId() );

//        Set< Integer > usersWithAccountInfo = new TreeSet<>();
//        Set< AccountInfo > seenAccountInfo = new TreeSet<>();
        
        
        
        for ( AccountInfo accountInfo : accountInfoMap.values() ) {

//            if ( !seenAccountInfo.contains( accountInfo ) ) {
//                seenAccountInfo.add( accountInfo );
//                fullHistory.addAccountInfo( accountInfo );
//            }
            fullHistory.addOrderMapWithAccountInfo( this.orderMapper.readAllByAccountInfoIdFull( accountInfo ) );
            
            
        }
        
        return fullHistory;
    }
    
}
