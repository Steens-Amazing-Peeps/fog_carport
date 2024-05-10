package app.web.constants.routing;

public interface WebPages
{     //Alle vores websides sider, inklusiv 'send' sider, hvis du kan se det I browseren oppe i URL, så er det en side og skal være her :)
    
    String INDEX_GET_PAGE = "/";
//    String INDEX_POST_PAGE = ;
    
    //Header only

    String LOGOUT_POST_PAGE = "/logout/";
  
    //Feedback
    String FEEDBACK_GET_PAGE = "/feedback";
    String FEEDBACK_POST_PAGE = FEEDBACK_GET_PAGE;

    
    
    //Account
    String LOGIN_GET_PAGE = "/login/";
    String LOGIN_POST_PAGE = LOGIN_GET_PAGE;
    
    String CREATE_ACCOUNT_GET_PAGE = "/create-account/";
    String CREATE_ACCOUNT_POST_PAGE = CREATE_ACCOUNT_GET_PAGE;
    
    String RESET_ACCOUNT_PASSWORD_GET_PAGE = "/reset-account-password/";
    String RESET_ACCOUNT_PASSWORD_POST_PAGE = RESET_ACCOUNT_PASSWORD_GET_PAGE + "";
    
    
    //BuyFlow
    String CARPORT_1_INFO_GET_PAGE = "/carport/info/";
    String CARPORT_1_INFO_BACK_POST_PAGE = CARPORT_1_INFO_GET_PAGE + "back";
    String CARPORT_1_INFO_CONFIRM_POST_PAGE = CARPORT_1_INFO_GET_PAGE + "confirm";
    
    String CARPORT_2_DRAWING_GET_PAGE = "/carport/drawing/";
    String CARPORT_2_DRAWING_BACK_POST_PAGE = CARPORT_2_DRAWING_GET_PAGE + "back";
    String CARPORT_2_DRAWING_CONFIRM_POST_PAGE = CARPORT_2_DRAWING_GET_PAGE + "confirm";
    
    String CARPORT_3_ACCOUNT_INFO_GET_PAGE = "/carport/account-info/";
    String CARPORT_3_ACCOUNT_INFO_BACK_POST_PAGE = CARPORT_3_ACCOUNT_INFO_GET_PAGE + "back";
    String CARPORT_3_ACCOUNT_INFO_CONFIRM_POST_PAGE = CARPORT_3_ACCOUNT_INFO_GET_PAGE + "confirm";
    
    
    String CARPORT_4_REVIEW_AND_CONFIRM_GET_PAGE = "/carport/review-and-confirm/";
    String CARPORT_4_REVIEW_AND_CONFIRM_BACK_POST_PAGE = CARPORT_4_REVIEW_AND_CONFIRM_GET_PAGE + "back";
    String CARPORT_4_REVIEW_AND_CONFIRM_CONFIRM_POST_PAGE = CARPORT_4_REVIEW_AND_CONFIRM_GET_PAGE  + "confirm";
    
    
    String CARPORT_5_RECEIPT_GET_PAGE = "/carport/receipt/";
    String CARPORT_5_RECEIPT_BACK_POST_PAGE = CARPORT_5_RECEIPT_GET_PAGE + "back";
    String CARPORT_5_RECEIPT_CONFIRM_POST_PAGE = CARPORT_5_RECEIPT_GET_PAGE + "confirm";
    
    
    String CARPORT_BILL_PAY_UP_GET_PAGE = "/carport/bill/";
    String CARPORT_BILL_PAY_UP_BACK_POST_PAGE = CARPORT_BILL_PAY_UP_GET_PAGE + "back";
    String CARPORT_BILL_PAY_UP_CONFIRM_POST_PAGE = CARPORT_BILL_PAY_UP_GET_PAGE + "confirm";
    
    
    String CARPORT_ORDER_HISTORY_GET_PAGE = "/carport/order-history/";
    
    
    
    //Admin Only
    String EDIT_BUILDING_MATERIALS_GET_PAGE = "/edit-building-materials/";
    String EDIT_BUILDING_MATERIALS_POST_PAGE = EDIT_BUILDING_MATERIALS_GET_PAGE + "";
    
    
    
}
    
    

