package br.upe.pweb.servlet.nasa_servlet_api.repository;

import java.sql.*;
import java.util.ArrayList;

import br.upe.pweb.servlet.nasa_servlet_api.models.NasaUserModel;
import br.upe.pweb.servlet.nasa_servlet_api.models.NasaUserModel.UserEntity;
import br.upe.pweb.servlet.nasa_servlet_api.services.JdbcService;
import br.upe.pweb.servlet.nasa_servlet_api.interfaces.INasaRepository;

public class UserRepository implements INasaRepository<NasaUserModel>, AutoCloseable {
  
  private Connection m_Connection;

  /**
   * O construtor a conexão com o banco de dados Postgres
   * utilizando do driver JDBC apropriado.
   */
  public UserRepository(){
    JdbcService jdbc = new JdbcService();
    try {
      Class.forName("org.postgresql.Driver");
      this.m_Connection = DriverManager.getConnection(
        jdbc.getDatasourceUrl(), 
        jdbc.getDatasourceUsername(), 
        jdbc.getDatasourcePassword());
    } catch (Exception e){
      //TODO
      e.printStackTrace();
    }
  }

  /**
   * Cria uma nova instância do repositório e retorna para o solicitante.
   * 
   * @return Uma nova instância do repositório do usuário.
   */
  public static UserRepository getRepository(){
    return new UserRepository();
  }

  /**
   * Insere um usuário no repositório.
   */
  @Override
  public void insert(NasaUserModel entity) {
    String sql = "INSERT INTO nasa_users (user_id, user_first_name, user_last_name, user_email) VALUES (?,?,?,?)";
    try (PreparedStatement statement = m_Connection.prepareStatement(sql)) {
      statement.setString(1, entity.getUser().getId());
      statement.setString(2, entity.getFirstName());
      statement.setString(3, entity.getLastName());
      statement.setString(4, entity.getUserEmail());
      statement.executeUpdate();
    } catch (SQLException e) {
      //TODO
    }
  }

  /**
   * Atualiza um usuário no repositório.
   */
  @Override
  public void update(NasaUserModel entity) {
    String sql = "UPDATE nasa_users SET user_id = ?, user_first_name = ?, user_last_name = ?, user_email = ? WHERE user_id = ?";
    try (PreparedStatement statement = m_Connection.prepareStatement(sql)) {
      statement.setString(1, entity.getUser().getId());
      statement.setString(2, entity.getFirstName());
      statement.setString(3, entity.getLastName());
      statement.setString(4, entity.getUserEmail());
      statement.setString(5, entity.getUser().getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      //TODO
    }
  }

  /**
   * Deleta um usuário do repositório.
   */
  @Override
  public void delete(String entityId) {
    String sql = "DELETE FROM nasa_users WHERE user_id = ?";
    try (PreparedStatement statement = m_Connection.prepareStatement(sql)) {
      statement.setString(1, entityId);
      statement.executeUpdate();
    } catch (SQLException e) {
      //TODO
    }
  }

  /**
   * Seleciona todos os usuários do repositório.
   */
  @Override
  public ArrayList<NasaUserModel> select() {
    String sql = "SELECT * FROM nasa_users";
    try (PreparedStatement statement = m_Connection.prepareStatement(sql)) {
      ResultSet result = statement.executeQuery();
      ArrayList<NasaUserModel> usersList = new ArrayList<>();
      while (result.next()){
        usersList.add(new NasaUserModel()
          .setFirstName(result.getString(2))
          .setLastName(result.getString(3))
          .setUserEmail(result.getString(4))
          .setUser(new UserEntity()
            .setId(result.getString(1))));
      }
      return usersList;
    } catch (SQLException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Seleciona um usuário específico do repositório pelo seu ID.
   */
  @Override
  public NasaUserModel select(String entityId) {
    String sql = "SELECT * FROM nasa_users WHERE user_id = ? ORDER BY user_id ASC LIMIT 1";
    try (PreparedStatement statement = m_Connection.prepareStatement(sql)) {
      statement.setString(1, entityId);
      ResultSet result = statement.executeQuery();
      if (result.next()){
        return new NasaUserModel()
          .setFirstName(result.getString(2))
          .setLastName(result.getString(3))
          .setUserEmail(result.getString(4))
          .setUser(new UserEntity()
            .setId(result.getString(1)));
      }
    } catch (SQLException e) {
      //TODO
    }
    return null;
  }

  /**
   * Finaliza a conexão com o banco de dados Postgres.
   * 
   * @throws Exception
   */
  @Override
  public void close() throws Exception {
    m_Connection.close();
  }
  
}
