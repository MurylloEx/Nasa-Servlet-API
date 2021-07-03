package br.upe.pweb.servlet.servlet_project.services;

public class NasaSingleton {
  
  /** Instância do serviço da Nasa. */
  private static NasaService s_NasaServiceInstance = null;

  /**
   * Retorna sempre a mesma instância do serviço.
   * 
   * @return A instância do serviço da Nasa.
   */
  public static NasaService getInstance(){
    if (s_NasaServiceInstance == null){
      s_NasaServiceInstance = new NasaService();
    }
    return s_NasaServiceInstance;
  }

}
