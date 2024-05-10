package app.web.constants.routing;

public interface WebHtml
{     //Alle vores HTML filer's path, bruges I controller.
    
    String INDEX_HTML = "index.html";
    
    //Account
    String LOGIN_HTML = PathParts.USERS + PathParts.ACCOUNT + "login.html";
    String CREATE_ACCOUNT_HTML = PathParts.USERS + PathParts.ACCOUNT + "create.html";
    String RESET_HTML = PathParts.USERS + PathParts.ACCOUNT + "reset.html";
    
    //BuyFlow
    String CARPORT_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport.html";
    String CONFIRM_CARPORT_HTML = PathParts.USERS + PathParts.BUY_FLOW + "confirm-carport.html";
    String HISTORY_HTML = PathParts.USERS + PathParts.BUY_FLOW + "history.html";
    
    interface PathParts //Makes renaming folders real easy
    {
        
        //Top level directories
        String USERS = "users/";
        String ADMINS = "admins/";
        
        //Categories
        String ACCOUNT = "account/";
        String BUY_FLOW = "buyFlow/";
        
    }
    
}
