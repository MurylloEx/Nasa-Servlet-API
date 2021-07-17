package br.upe.pweb.servlet.nasa_servlet_api.services;

public class JdbcService {

  public String getDatasourceUrl() {
    return "jdbc:postgresql://localhost/nasa_database";
  }

  public String getDatasourceUsername() {
    return "postgres";
  }

  public String getDatasourcePassword() {
    return "es2021";
  }

}
