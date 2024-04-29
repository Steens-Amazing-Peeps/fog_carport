package app.web.persistence;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Allows you to get connection from the private connectionPool no matter where you are
 *
 * Does not allow you to actually close the connection or do anything else
 *
 * ( unless you cast )
 */
public interface GetConnectionIf
{
    
    Connection getConnection() throws SQLException;
    
}
