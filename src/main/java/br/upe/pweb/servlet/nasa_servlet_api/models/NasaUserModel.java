package br.upe.pweb.servlet.nasa_servlet_api.models;

public class NasaUserModel extends NasaEntity {
  
  public class UserEntity {

    private String id;

    public String getId(){
      return this.id;
    }
  
    public void setId(String value){
      this.id = value;
    }

  }
  
  private String firstName;
  private String lastName;
  private String userEmail;
  private UserEntity user;


  public String getFirstName(){
    return this.firstName;
  }

  public String getLastName(){
    return this.lastName;
  }

  public String getUserEmail(){
    return this.userEmail;
  }

  public void setFirstName(String value){
    this.firstName = value;
  }

  public void setLastName(String value){
    this.lastName = value;
  }

  public void setUserEmail(String value){
    this.userEmail = value;
  }

  public UserEntity getUser(){
    return this.user;
  }

  public void setUser(UserEntity value){
    this.user = value;
  }

  public boolean isValid(){
    return (this.firstName != null) && 
      (this.lastName != null) && 
      (this.userEmail != null);
  }

}
