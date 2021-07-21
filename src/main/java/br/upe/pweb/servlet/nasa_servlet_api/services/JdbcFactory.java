package br.upe.pweb.servlet.nasa_servlet_api.services;

import java.sql.*;

public class JdbcFactory {

  public static String getDatasourceUrl() {
    return "jdbc:postgresql://localhost/";
  }

  public static String getDatasourceUsername() {
    return "postgres";
  }

  public static String getDatasourcePassword() {
    return "es2021";
  }

  public static Connection createConnection(String datasourceUrl){
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
        datasourceUrl,
        getDatasourceUsername(),
        getDatasourcePassword());
    } catch (Exception e){
      e.printStackTrace();
    }
    return connection;
  }

  public static Connection createDatabaseIfNotExists(String databaseName){
    Connection connection = createConnection(getDatasourceUrl());
    try {
      try (Statement statement = connection.createStatement()){
        statement.executeUpdate("CREATE DATABASE " + databaseName);
      } catch (Exception e){}
      connection.close();
      connection = createConnection(getDatasourceUrl() + databaseName);
    } catch (Exception e){
      e.printStackTrace();
    }
    return connection;
  }

  public static void createTableIfNotExists(Connection connection, String tableSql){
    try{
      try (Statement statement = connection.createStatement()){
        statement.executeUpdate(tableSql);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
