package br.upe.pweb.servlet.nasa_servlet_api.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upe.pweb.servlet.nasa_servlet_api.services.NasaService;
import br.upe.pweb.servlet.nasa_servlet_api.services.NasaSingleton;

@WebServlet("/nasa/api/mars-rover")
public class NasaMarsRoverServlet extends HttpServlet {
 
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    NasaService nasaService = NasaSingleton.getInstance();
    if (req.getHeader("Authorization") == null){
      /** Verifica se o Token da API foi especificado, caso não retorna Unauthorized. */
      res.sendError(401, "Você não possui uma chave de API válida.");
    } else {
      res.setHeader("Content-Type", "application/json; charset=utf-8;");
      res.getWriter().write(nasaService.fetchMarsRoverPhotos(
        req.getHeader("Authorization").replace("Bearer ", ""), req.getParameterMap()));
    }
  }

}
