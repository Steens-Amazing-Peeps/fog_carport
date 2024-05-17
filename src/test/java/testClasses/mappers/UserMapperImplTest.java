package testClasses.mappers;


import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.DataStore;
import app.web.persistence.mappers.UserMapper;

import java.util.Map;

public class UserMapperImplTest implements UserMapper
{
    
    private String actualEmail;
    private int expectedCreateId;
    
    private Map< Integer, User > expectedTestMap;
    
    public String getActualEmail()
    {
        return this.actualEmail;
    }
    
    public int getExpectedCreateId()
    {
        return this.expectedCreateId;
    }
    
    public void setExpectedCreateId( int expectedCreateId )
    {
        this.expectedCreateId = expectedCreateId;
    }
    
    public Map< Integer, User > getExpectedTestMap()
    {
        return this.expectedTestMap;
    }
    
    public void setExpectedTestMap( Map< Integer, User > expectedTestMap )
    {
        this.expectedTestMap = expectedTestMap;
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
    
    }
    
    @Override
    public int create( User user ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        user.setUserId( this.expectedCreateId );
        return 1;
    }
    
    @Override
    public Map< Integer, User > readAll() throws DatabaseException
    {
        return null;
    }
    
    @Override
    public Map< Integer, User > readAllByEmail( String email ) throws DatabaseException
    {
        this.actualEmail = email;
        return this.expectedTestMap;
    }
    
    @Override
    public User readSingle( Integer id ) throws DatabaseException
    {
        return null;
    }
    
    @Override
    public int update( User user ) throws DatabaseException, UnexpectedResultDbException
    {
        return 0;
    }
    
    @Override
    public int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        return 0;
    }
    
}
