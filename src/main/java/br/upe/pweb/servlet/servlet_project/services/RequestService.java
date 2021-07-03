package br.upe.pweb.servlet.servlet_project.services;

import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import okhttp3.*;

public class RequestService {

  /**
   * Essa função realiza a análise e conversão do Map<String, String> obtido
   * para a query string em formato ?param1=value1&param2=value2&...
   * 
   * @param queryString O hashmap ou qualquer objeto derivado de Map que contenha os valores da query string.
   * @return Retorna uma query string codificada.
   */
  private static String getEncodedQueryString(Map<String, String> queryString){
    StringBuilder qsBuilder = new StringBuilder();
    if (queryString.size() > 0){
      qsBuilder.append('?');
    }
    for (Map.Entry<String, String> entry : queryString.entrySet()){
      if (qsBuilder.length() > 1){
        qsBuilder.append('&');
      }
      qsBuilder.append(
        URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + '=' + 
        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
    }
    return qsBuilder.toString();
  }

  /**
   * Essa função realiza uma requisição HTTP GET.
   * 
   * @param url A URL de destino.
   * @param headers Os cabeçalhos em formato de Map.
   * @param queryString A Query String em formato de Map.
   * @return A resposta em formato de string.
   */
  public static String get(
    String url, 
    Map<String, String> headers,
    Map<String, String> queryString)
  {
    try {
      OkHttpClient client = new OkHttpClient();
      Request.Builder requestBuilder = new Request.Builder()
        .url(url + getEncodedQueryString(queryString));

      for (Map.Entry<String, String> entry : headers.entrySet()){
        requestBuilder.addHeader(entry.getKey(), entry.getValue());
      }

      Request request = requestBuilder.build();
      try (Response response = client.newCall(request).execute()){
        return response.body().string();
      }
    } catch (Exception ex){
      return null;
    }
  }

  /**
   * Essa função realiza uma requisição HTTP POST.
   * 
   * @param url A URL de destino.
   * @param body O corpo da requisição.
   * @param headers Os cabeçalhos em formato de Map.
   * @param queryString A Query String em formato de Map.
   * @return A resposta em formato de string.
   */
  public static String post(
    String url, 
    RequestBody body,
    Map<String, String> headers, 
    Map<String, String> queryString)
  {
    try {
      OkHttpClient client = new OkHttpClient();
      Request.Builder requestBuilder = new Request.Builder()
        .url(url + getEncodedQueryString(queryString))
        .post(body);

      for (Map.Entry<String, String> entry : headers.entrySet()){
        requestBuilder.addHeader(entry.getKey(), entry.getValue());
      }

      Request request = requestBuilder.build();
      try (Response response = client.newCall(request).execute()){
        return response.body().string();
      }
    } catch (Exception ex){
      return null;
    }
  }

  /**
   * Essa função realiza uma requisição HTTP PUT.
   * 
   * @param url A URL de destino.
   * @param body O corpo da requisição.
   * @param headers Os cabeçalhos em formato de Map.
   * @param queryString A Query String em formato de Map.
   * @return A resposta em formato de string.
   */
  public static String put(
    String url, 
    RequestBody body,
    Map<String, String> headers, 
    Map<String, String> queryString)
  {
    try {
      OkHttpClient client = new OkHttpClient();
      Request.Builder requestBuilder = new Request.Builder()
        .url(url + getEncodedQueryString(queryString))
        .put(body);

      for (Map.Entry<String, String> entry : headers.entrySet()){
        requestBuilder.addHeader(entry.getKey(), entry.getValue());
      }

      Request request = requestBuilder.build();
      try (Response response = client.newCall(request).execute()){
        return response.body().string();
      }
    } catch (Exception ex){
      return null;
    }
  }

  /**
   * Essa função realiza uma requisição HTTP DELETE.
   * 
   * @param url A URL de destino.
   * @param headers Os cabeçalhos em formato de Map.
   * @param queryString A Query String em formato de Map.
   * @return A resposta em formato de string.
   */
  public static String delete(
    String url, 
    Map<String, String> headers, 
    Map<String, String> queryString)
  {
    try {
      OkHttpClient client = new OkHttpClient();
      Request.Builder requestBuilder = new Request.Builder()
        .url(url + getEncodedQueryString(queryString))
        .delete();

      for (Map.Entry<String, String> entry : headers.entrySet()){
        requestBuilder.addHeader(entry.getKey(), entry.getValue());
      }

      Request request = requestBuilder.build();
      try (Response response = client.newCall(request).execute()){
        return response.body().string();
      }
    } catch (Exception ex){
      return null;
    }
  }

}
