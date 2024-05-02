package app.web.constants.attributes;



import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.persistence.mappers.UserMapper;
import app.web.persistence.mappers.UserMapperImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public interface WebGlobalAttributes
{
    
    //Should be global but we suck
    String userMap = "userMap";
    Map< Integer, User > USER_MAP = new LinkedHashMap<>();
    

   
    
    
    
    
    static void startUp( UserMapper userMapper )
    {
        try {
            USER_MAP.putAll( userMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
    
    }
    
}
