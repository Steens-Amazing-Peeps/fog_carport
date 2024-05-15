package app.web.constants.attributes;

public interface WebAttributes
{//Alle CTX Attributes, bruges I controller og thymeleaf
    
    // NOTE: Not SESSION attributes, just attributes
    
    String msg = "msg";
    
    
    //carport1Info
    String msgCarportHeight = "msgCarportHeight";
    String msgCarportLength = "msgCarportLength";
    String msgCarportWidth = "msgCarportWidth";
    
    //carport3AccountInfo
    String msgFullName = "msgFullName";
    String msgAddress = "msgAddress";
    String msgZipCode = "msgZipCode";
    String msgCity = "msgCity";
    String msgPhone = "msgPhone";
    String msgEmail= "msgEmail";
    String msgConsentToSpam= "msgConsentToSpam";
    
    
    String success = "success";
    
}
