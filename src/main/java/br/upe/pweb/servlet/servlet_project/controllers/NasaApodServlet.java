package br.upe.pweb.servlet.servlet_project.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upe.pweb.servlet.servlet_project.services.NasaService;
import br.upe.pweb.servlet.servlet_project.services.NasaSingleton;

@WebServlet("/nasa/api/astronomic")
public class NasaApodServlet extends HttpServlet {
  
  /**
   * Esse é o Handler de requisições GET, onde a API Astronomic 
   * Picture of Day é chamada para obter a foto astronômica do dia.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    NasaService nasaService = NasaSingleton.getInstance();
    if (req.getHeader("Authorization") == null){
      /** Verifica se o Token da API foi especificado, caso não retorna Unauthorized. */
      res.sendError(401, "Você não possui uma chave de API válida.");
    } else {
      res.setHeader("Content-Type", "application/json; charset=utf-8;");
      res.getWriter().write(nasaService.fetchApod(
        req.getHeader("Authorization").replace("Bearer ", ""), req.getParameterMap()));
    }
  }

}
