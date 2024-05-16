package app.web.entities;

public class AccountInfo
{ //TODO: Ponder how to best implement this class/entity/feature, probably shouldn't be an inner class

    private Integer userId;
    private Integer contactId;
    private String fullName;
    private String address;
    private Integer zip;
    private String city;
    private Integer phoneNumber;
    private String email;
    
    private Boolean consentToSpam;

    private boolean hasAnAccount;   //TODO is this needed?
    
    
    
    public String toStringPretty( StringBuilder stringBuilder )
    {
        stringBuilder.append( this.toString() ).append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    @Override
    public String toString()
    {
        return "ContactInfo{" +
               "contactId=" + this.contactId +
               ", fullName='" + this.fullName + '\'' +
               ", address='" + this.address + '\'' +
               ", zip=" + this.zip +
               ", city='" + this.city + '\'' +
               ", phoneNumber=" + this.phoneNumber +
               ", email='" + this.email + '\'' +
               ", hasAnAccount=" + this.hasAnAccount +
               '}';
    }
    
    
    //Getters and Setters
    public Integer getUserId()
    {
        return this.userId;
    }
    
    public void setUserId( Integer userId )
    {
        this.userId = userId;
    }
    
    public Integer getContactId() {
        return this.contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return this.zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getConsentToSpam()
    {
        return this.consentToSpam;
    }
    
    public void setConsentToSpam( Boolean consentToSpam )
    {
        this.consentToSpam = consentToSpam;
    }

    public boolean isHasAnAccount()
    {
        return this.hasAnAccount;
    }   //TODO Needed or?
    
    public void setHasAnAccount( boolean hasAnAccount )
    {
        this.hasAnAccount = hasAnAccount;
    }   //TODO Needed or?
    

    
}
