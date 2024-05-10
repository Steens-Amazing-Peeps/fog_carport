package app.web.constants.routing;

public interface WebHtml
{     //Alle vores HTML filer's path, bruges I controller.
    
    String INDEX_HTML = "index.html";
    
    //Account
    String LOGIN_HTML = PathParts.USERS + PathParts.ACCOUNT + "login.html";
    String CREATE_ACCOUNT_HTML = PathParts.USERS + PathParts.ACCOUNT + "create.html";
    String RESET_ACCOUNT_PASSWORD_HTML = PathParts.USERS + PathParts.ACCOUNT + "reset-account-password.html";
    
    //BuyFlow
    String CARPORT_1_INFO_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-1-info.html";
    String CARPORT_2_DRAWING_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-2-drawing.html";
    String CARPORT_3_ACCOUNT_INFO_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-3-account-info.html";
    String CARPORT_4_REVIEW_AND_CONFIRM_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-4-review-and-confirm.html";
    String CARPORT_5_RECEIPT_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-5-receipt.html";
    
    String CARPORT_BILL_PAY_UP_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-bill-pay-up.html";
    String CARPORT_ORDER_HISTORY_HTML = PathParts.USERS + PathParts.BUY_FLOW + "carport-order-history.html";
    
    
    //Admins
    String EDIT_BUILDING_MATERIALS_HTML = PathParts.ADMINS + "edit-building-materials.html";
    
    
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
