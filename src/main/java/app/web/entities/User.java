package app.web.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class User
{
    
    private Integer userId;  // t
    private String email;  // t
    private String password;  // t
    private String role;  // t
    
    
    
    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof User otherUser ) {
            
            if (  !Objects.equals( otherUser.getUserId(), this.getUserId() ) ){
                return false;
            }
            
            if (  !Objects.equals( otherUser.getEmail(), this.getEmail() ) ){
                return false;
            }
            
            if (  !Objects.equals( otherUser.getPassword(), this.getPassword() ) ){
                return false;
            }
            
            if (  !Objects.equals( otherUser.getRole(), this.getRole() ) ){
                return false;
            }
            
            return true;
        }
        
        return false;
    }
    
    
    public String getString(){
        return "Bruger Id: " + this.userId +
               " - " + this.email  +
               " - " + this.role;
    }
    @Override
    public String toString()
    {
        return "User{" +
               "userId=" + this.userId +
               ", email='" + this.email + '\'' +
               ", password='" + this.password + '\'' +
               ", role=" + this.role +
               '}';
    }
    
    public Integer getUserId()
    {
        return this.userId;
    }
    
    public void setUserId( Integer userId )
    {
        this.userId = userId;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail( String email )
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public void setPassword( String password )
    {
        this.password = password;
    }
    
    public String getRole()
    {
        return this.role;
    }
    
    public void setRole( String role )
    {
        this.role = role;
    }
    
    

    
}