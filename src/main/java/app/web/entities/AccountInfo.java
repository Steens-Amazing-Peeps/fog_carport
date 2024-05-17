package app.web.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Note: this class has a natural ordering that is
 * inconsistent with equals.
 **/
public class AccountInfo implements Comparable< AccountInfo >
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
               '}';
    }
    
    @Override
    public boolean equals( Object object )
    {
        if ( this == object ) {
            return true;
        }
        if ( !( object instanceof AccountInfo accountInfo ) ) {
            return false;
        }
        return Objects.equals( this.userId, accountInfo.userId ) && Objects.equals( this.fullName, accountInfo.fullName ) && Objects.equals( this.address, accountInfo.address ) && Objects.equals( this.zip, accountInfo.zip ) && Objects.equals( this.city, accountInfo.city ) && Objects.equals( this.phoneNumber, accountInfo.phoneNumber ) && Objects.equals( this.email, accountInfo.email ) && Objects.equals( this.consentToSpam, accountInfo.consentToSpam );
    }
    
    @Override
    public int compareTo( @NotNull AccountInfo o )
    {
        int compareValue = this.userId - o.userId;
        
        compareValue = compareValue + this.fullName.compareTo( o.fullName );
        
        compareValue = compareValue + this.address.compareTo( o.address );
        
        compareValue = compareValue + this.zip - o.zip;
        
        compareValue = compareValue + this.city.compareTo(o.city);
        
        compareValue = compareValue + this.phoneNumber - o.phoneNumber;
        
        compareValue = compareValue + this.email.compareTo( o.email);
        
        compareValue = compareValue + this.consentToSpam.compareTo( o.consentToSpam );
        
        return compareValue;
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
    
    public Integer getContactId()
    {
        return this.contactId;
    }
    
    public void setContactId( Integer contactId )
    {
        this.contactId = contactId;
    }
    
    public String getFullName()
    {
        return this.fullName;
    }
    
    public void setFullName( String fullName )
    {
        this.fullName = fullName;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setAddress( String address )
    {
        this.address = address;
    }
    
    public Integer getZip()
    {
        return this.zip;
    }
    
    public void setZip( Integer zip )
    {
        this.zip = zip;
    }
    
    public String getCity()
    {
        return this.city;
    }
    
    public void setCity( String city )
    {
        this.city = city;
    }
    
    public Integer getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber( Integer phoneNumber )
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail( String email )
    {
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
    
    
    
}
