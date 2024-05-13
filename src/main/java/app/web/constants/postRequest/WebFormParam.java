package app.web.constants.postRequest;

public interface WebFormParam
{ //Alle CTX Form Param (Når vi sender en form med HTML/Thymeleaf), bruges I controller og thymeleaf
    
    String email = "email";
    String password = "password";
    String repeatPassword = "verify-password";
    
    String carportWidth = "carportWidth";
    String carportLength = "carportLength";
    String carportHeight = "carportHeight";
    
    String carportSend = "carportSend";
    
    String feedbackSend = "feedbackSend";
    
    
    
    
    
    
    
    
}
