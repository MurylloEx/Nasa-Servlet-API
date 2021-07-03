package br.upe.pweb.servlet.servlet_project.repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import br.upe.pweb.servlet.servlet_project.models.NasaUserModel;
import br.upe.pweb.servlet.servlet_project.interfaces.INasaRepository;

public class UserRepository implements INasaRepository<NasaUserModel> {

  private Hashtable<String, NasaUserModel> m_UserStorage = new Hashtable<>();
  private static UserRepository s_UserRepositoryInstance = null;

  /**
   * Retorna uma instância única do repositório do usuário.
   * 
   * @return Uma instância do repositório do usuário, controlada pelo Singleton.
   */
  public static UserRepository getRepository(){
    if (s_UserRepositoryInstance == null){
      s_UserRepositoryInstance = new UserRepository();
    }
    return s_UserRepositoryInstance;
  }

  /**
   * Insere um usuário no repositório.
   */
  @Override
  public void insert(NasaUserModel entity) {
    this.m_UserStorage.put(entity.getUser().getId(), entity);
  }

  /**
   * Atualiza um usuário no repositório.
   */
  @Override
  public void update(NasaUserModel entity) {
    for (Map.Entry<String, NasaUserModel> entry : this.m_UserStorage.entrySet()){
      if (entity.getUser().getId().equals(entry.getKey())){
        this.m_UserStorage.put(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Deleta um usuário do repositório.
   */
  @Override
  public void delete(String entityId) {
    for (Map.Entry<String, NasaUserModel> entry : this.m_UserStorage.entrySet()){
      if (entityId.equals(entry.getKey())){
        this.m_UserStorage.remove(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Seleciona todos os usuários do repositório.
   */
  @Override
  public ArrayList<NasaUserModel> select() {
    ArrayList<NasaUserModel> allUsers = new ArrayList<NasaUserModel>();
    for (Map.Entry<String, NasaUserModel> entry : this.m_UserStorage.entrySet()){
      allUsers.add(entry.getValue());
    }
    return allUsers;
  }

  /**
   * Seleciona um usuário específico do repositório pelo seu ID.
   */
  @Override
  public NasaUserModel select(String entityId) {
    for (Map.Entry<String, NasaUserModel> entry : this.m_UserStorage.entrySet()){
      if (entityId.equals(entry.getKey())){
        return entry.getValue();
      }
    }
    return null;
  }
  
  

}
