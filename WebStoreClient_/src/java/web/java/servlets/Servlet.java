/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.java.controllers.Products;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

         @Override
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
         }

         @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
                  
                  response.setContentType("application/json");
                  String type = request.getParameter("manuf");
                  
                  
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }

}