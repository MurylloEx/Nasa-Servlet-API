package br.upe.pweb.servlet.nasa_servlet_api.services;

import com.google.gson.Gson;

public class JsonService {
 
  /**
   * Esta função tem como objetivo analisar uma string literal como um objeto.
   * 
   * @param <T> O tipo genérico de retorno da função.
   * @param body A string correspondente ao corpo do objeto.
   * @param entityType O tipo da entidade a ser desserializada.
   * @return Retorna um objeto de tipo T genérico.
   */
  @SuppressWarnings("unchecked")
  public static <T> T parse(String body, Class<?> entityType){
    try{
      return (T)(new Gson()).fromJson(body, entityType);
    } catch (Exception ex){
      return null;
    }
  }

  /**
   * Esta função tem como objetivo representar um objeto simples em uma string literal.
   * 
   * @param <T> Tipo do objeto;
   * @param obj O objeto a ser representado como string.
   * @return A string que representa o objeto.
   */
  public static <T> String stringify(T obj, Class<?> type){
    return (new Gson()).toJson(obj, type);
  }

}
