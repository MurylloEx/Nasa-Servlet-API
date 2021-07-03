package br.upe.pweb.servlet.servlet_project.services;

import java.util.*;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class NasaService {
  
  /**Endpoint de consulta aos bancos de dados da Nasa. */
  private final String NASA_API_ADDRESS = "https://api.nasa.gov/";
  private final String NASA_APOD_API = "planetary/apod";
  private final String NASA_MARS_ROVER_API = "mars-photos/api/v1/rovers/curiosity/photos";
  private final String NASA_ASTEROIDS_API = "neo/rest/v1/feed";

  /**Endpoint de autenticação do servidor de API da Nasa. */
  private final String NASA_AUTH_ADDRESS = "https://api.data.gov/";
  private final String NASA_AUTH_API = "api-umbrella/v1/";

  public NasaService(){ /* Não utilizado */ }

  /**
   * Cria um processo de autenticação e retorna um token de acesso
   * aos endpoints de consulta aos bancos de dados da Nasa.
   * 
   * @param userFirstName Primeiro nome do usuário.
   * @param userLastName Sobrenome do usuário.
   * @param userEmail Endereço de e-mail do usuário.
   * @return Retorna uma string referente ao objeto de resposta da API.
   */
  public String authenticate(
    String userFirstName,
    String userLastName,
    String userEmail)
  {
    Map<String, String> headers = new Hashtable<String, String>();

    headers.put("Pragma", "no-cache");
    headers.put("Cache-Control", "no-cache");
    headers.put("sec-ch-ua-mobile", "?0");
    headers.put("Referer", "https://api.nasa.gov/");

    Map<String, String> queryString = new Hashtable<String, String>();
    
    queryString.put("api_key", "jfr9uihqvncOuii7lda5bDlsvOIDePcKTLWlzLte");

    RequestBody formBody = new FormBody.Builder()
      .add("user[first_name]", userFirstName)
      .add("user[last_name]", userLastName)
      .add("user[email]", userEmail)
      .add("user[terms_and_conditions]", "1")
      .add("options[contact_url]", "mailto:nasa-data@lists.arc.nasa.gov")
      .add("options[site_name]", "https://api.nasa.gov")
      .add("options[verify_email]", "false")
      .build();

    return RequestService.post(
      NASA_AUTH_ADDRESS + NASA_AUTH_API + "users.json", 
      formBody, headers, queryString);
  }

  /**
   * Retorna os dados da API APOD (Astronomic Picture of Day) 
   * publicadas diariamente pela NASA.
   * 
   * @param apiKey Chave da API.
   * @param apiQueryString Parâmetros da Query String.
   * @return Retorna uma string referente ao objeto de resposta da API.
   */
  public String fetchApod(String apiKey, Map<String, String[]> apiQueryString) {
    Map<String, String> headers = new Hashtable<String, String>();

    headers.put("Pragma", "no-cache");
    headers.put("Cache-Control", "no-cache");
    headers.put("sec-ch-ua-mobile", "?0");
    headers.put("Referer", "https://api.nasa.gov/");

    Map<String, String> queryString = new Hashtable<String, String>();
    
    for (Map.Entry<String, String[]> entry : apiQueryString.entrySet()){
      queryString.put(entry.getKey(), entry.getValue()[0]);
    }

    queryString.put("api_key", apiKey);

    return RequestService.get(
      NASA_API_ADDRESS + NASA_APOD_API, headers, queryString);
  }
  
  /**
   * Retorna os dados da API Mars Rover Photos (Robô em Marte) que envia
   * fotos diariamente de Marte para a Terra.
   * 
   * @param apiKey Chave da API.
   * @param apiQueryString Parâmetros da Query String.
   * @return Retorna uma string referente ao objeto de resposta da API.
   */
  public String fetchMarsRoverPhotos(String apiKey, Map<String, String[]> apiQueryString) {
    Map<String, String> headers = new Hashtable<String, String>();

    headers.put("Pragma", "no-cache");
    headers.put("Cache-Control", "no-cache");
    headers.put("sec-ch-ua-mobile", "?0");
    headers.put("Referer", "https://api.nasa.gov/");

    Map<String, String> queryString = new Hashtable<String, String>();
    
    for (Map.Entry<String, String[]> entry : apiQueryString.entrySet()){
      queryString.put(entry.getKey(), entry.getValue()[0]);
    }

    queryString.put("api_key", apiKey);

    return RequestService.get(
      NASA_API_ADDRESS + NASA_MARS_ROVER_API, headers, queryString);
  }

  /**
   * Retorna os dados da API ASTEROIDS que nos diz quais os asteróides mais próximos
   * do globo terrestre, seu nível de perigo, seu diâmetro e distância em relação à Terra.
   * 
   * @param apiKey Chave da API.
   * @param apiQueryString Parâmetros da Query String.
   * @return Retorna uma string referente ao objeto de resposta da API.
   */
  public String fetchAsteroids(String apiKey, Map<String, String[]> apiQueryString) {
    Map<String, String> headers = new Hashtable<String, String>();

    headers.put("Pragma", "no-cache");
    headers.put("Cache-Control", "no-cache");
    headers.put("sec-ch-ua-mobile", "?0");
    headers.put("Referer", "https://api.nasa.gov/");

    Map<String, String> queryString = new Hashtable<String, String>();
    
    for (Map.Entry<String, String[]> entry : apiQueryString.entrySet()){
      queryString.put(entry.getKey(), entry.getValue()[0]);
    }

    queryString.put("api_key", apiKey);

    return RequestService.get(
      NASA_API_ADDRESS + NASA_ASTEROIDS_API, headers, queryString);
  }
  

}
