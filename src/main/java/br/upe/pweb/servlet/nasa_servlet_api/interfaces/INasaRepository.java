package br.upe.pweb.servlet.nasa_servlet_api.interfaces;

import java.util.ArrayList;

import br.upe.pweb.servlet.nasa_servlet_api.models.NasaEntity;

public interface INasaRepository <E extends NasaEntity> {
  
  public void insert(E entity);
	public void update(E entity);
	public void delete(String entityId);
	public ArrayList<E> select();
  public E select(String entityId);

}
