package app.web.constants.attributes;

public interface WebSessionAttributes
{ //Alle CTX Session Attributes, bruges I controller og thymeleaf

    String currentUser = "currentUser";
    
    String currentOrder = "currentOrder";
    String completedOrder = "completedOrder";
    
    String fullHistory = "fullHistory";    //Either a FullHistory or a Map<Integer, FullHistory>
    
}
