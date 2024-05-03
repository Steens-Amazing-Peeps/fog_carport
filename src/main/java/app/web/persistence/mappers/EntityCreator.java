package app.web.persistence.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface EntityCreator
{
    /**
     *Should always follow this rule
     *
     * NOTE: Entity = (user, wallet, orderLines ect.)
     *
     * @param rs
     * @return Entity
     * @throws SQLException
     */
    
    Object createEntity( ResultSet rs ) throws SQLException;
    
    
    /**
     *
     *
     *Should always follow this rule
     *
     * NOTE: Entity = (user, wallet, orderLines ect.)
     *
     * @param rs
     * @return LinkedHashMap<Integer,Entity>
     * @throws SQLException
     */
    Map<Integer, ?> createEntityMultiple( ResultSet rs ) throws SQLException;
    
    
    
    
    
    /**
     * Casting baby!
     *
     * @param entity (Entity)
     * @param id (ID to Set on Entity)
     * @return Entity with ID set
     */
    Object setId (Object entity, Integer id);
    
}
