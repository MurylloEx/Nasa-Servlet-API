package br.upe.pweb.servlet.servlet_project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upe.pweb.servlet.servlet_project.models.NasaUserModel;
import br.upe.pweb.servlet.servlet_project.repository.UserRepository;
import br.upe.pweb.servlet.servlet_project.services.JsonService;
import br.upe.pweb.servlet.servlet_project.services.NasaService;
import br.upe.pweb.servlet.servlet_project.services.NasaSingleton;

@WebServlet("/nasa/api/auth")
public class NasaAuthenticationServlet extends HttpServlet{

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    NasaService nasaService = NasaSingleton.getInstance();
    String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    NasaUserModel userData = JsonService.parse(reqBody, NasaUserModel.class);

    res.setHeader("Content-Type", "application/json; charset=utf-8;");
    if (userData.isValid()){
      String authResult = nasaService.authenticate(
        userData.getFirstName(), 
        userData.getLastName(), 
        userData.getUserEmail());

      NasaUserModel authenticatedUser = 
        JsonService.parse(authResult, NasaUserModel.class);

      userData.setUser(authenticatedUser.getUser());
      UserRepository.getRepository().insert(userData);

      res.getWriter().write(authResult);
    } else {
      res.sendError(400, "Requisição mal formada. Uma ou mais informações estavam faltando (firstName, lastName, userEmail).");
    }
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    UserRepository userRepository = UserRepository.getRepository();
    res.setHeader("Content-Type", "application/json; charset=utf-8;");
    if (req.getParameter("userId") != null){
      NasaUserModel userData = userRepository.select(req.getParameter("userId"));
      if (userData != null){
        res.getWriter().write(JsonService.stringify(userData, userData.getClass()));
      } else {
        res.sendError(404, "Usuário não encontrado no sistema! Verifique se o ID foi digitado corretamente.");
      }
    } else {
      res.getWriter().write(JsonService.stringify(userRepository.select(), ArrayList.class));
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    NasaUserModel userData = JsonService.parse(reqBody, NasaUserModel.class);
    UserRepository.getRepository().update(userData);
    res.setHeader("Content-Type", "application/json; charset=utf-8;");
    res.getWriter().write(reqBody);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setHeader("Content-Type", "application/json; charset=utf-8;");
    if (req.getParameter("userId") != null){
      UserRepository.getRepository().delete(req.getParameter("userId"));
      res.sendError(200, "Usuário excluído com sucesso!");
    } else {
      res.sendError(400, "Você precisa fornecer o ID do usuário a ser excluído.");
    }
  }

}
