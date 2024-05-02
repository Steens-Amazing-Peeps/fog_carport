package app.web.pageControllers.views;

import app.web.constants.attributes.WebAttributes;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UserFriendyException;
import io.javalin.http.Context;

public interface View
{
    
    void display( Context ctx );
    
    void redirect( Context ctx );
    
    default void displayCommonError( Context ctx, UserFriendyException e )
    {
        ctx.attribute( WebAttributes.msg, e.getUserMessage() );
        this.display( ctx );
    }
    
}
