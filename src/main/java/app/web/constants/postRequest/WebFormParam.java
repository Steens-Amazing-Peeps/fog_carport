package app.web.constants.postRequest;

public interface WebFormParam
{ //Alle CTX Form Param (Når vi sender en form med HTML/Thymeleaf), bruges I controller og thymeleaf
    
    //Login and Create Account
    String email = "email";
    String password = "password";
    
    //Create Account
    String repeatPassword = "verify-password";
    
    //Carport1Info
    String carportWidth = "carportWidth";
    String carportLength = "carportLength";
    String carportHeight = "carportHeight";
    String Message = "Message";
    
    //Carport3AccountInfo
    String fullName = "fullName";
    String address = "address";
    String zip = "zip";
    String city = "city";
    String phoneNumber = "phoneNumber";


    
    String carportSend = "carportSend";
    
    String feedbackSend = "feedbackSend";
    
    
    
    
    
    
    
    
}
