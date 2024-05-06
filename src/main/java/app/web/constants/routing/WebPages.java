package app.web.constants.routing;

public interface WebPages
{     //Alle vores websides sider, inklusiv 'send' sider, hvis du kan se det I browseren oppe i URL, så er det en side og skal være her :)
    
    String INDEX_GET_PAGE = "/";
//    String INDEX_POST_PAGE = ;
    
    String LOGIN_GET_PAGE = "/login";
    String LOGIN_POST_PAGE = LOGIN_GET_PAGE;
    
    String LOGOUT_POST_PAGE = "/logout";
    
    String CREATE_ACCOUNT_GET_PAGE = "/create-account";
    String CREATE_ACCOUNT_POST_PAGE = CREATE_ACCOUNT_GET_PAGE;

    String CARPORT_GET_PAGE = "/carport";
    
    String HISTORY_GET_PAGE = "/order-history";
    
    String CONFIRM_CARPORT_GET_PAGE = "/confirm-carport";
    
    String RESET_GET_PAGE = "/reset";
    

    
}
    
    

