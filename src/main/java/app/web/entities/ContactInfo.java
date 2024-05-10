package app.web.entities;

public class ContactInfo
{ //TODO: Ponder how to best implement this class/entity/feature, probably shouldn't be an inner class
    
    private boolean hasAnAccount;
    
    //Getters and Setters
    
    public boolean isHasAnAccount()
    {
        return this.hasAnAccount;
    }
    
    public void setHasAnAccount( boolean hasAnAccount )
    {
        this.hasAnAccount = hasAnAccount;
    }
    
}
