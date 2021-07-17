package br.upe.pweb.servlet.nasa_servlet_api.models;

public class NasaUserModel extends NasaEntity {
  
  public static class UserEntity {

    private String id;

    public String getId(){
      return this.id;
    }
  
    public UserEntity setId(String value){
      this.id = value;
      return this;
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

  public NasaUserModel setFirstName(String value){
    this.firstName = value;
    return this;
  }

  public NasaUserModel setLastName(String value){
    this.lastName = value;
    return this;
  }

  public NasaUserModel setUserEmail(String value){
    this.userEmail = value;
    return this;
  }

  public UserEntity getUser(){
    return this.user;
  }

  public NasaUserModel setUser(UserEntity value){
    this.user = value;
    return this;
  }

  public boolean isValid(){
    return (this.firstName != null) && 
      (this.lastName != null) && 
      (this.userEmail != null);
  }

}
