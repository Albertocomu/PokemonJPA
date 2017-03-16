/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.PBean;
import entities.Pokemon;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergiodiaz
 */
@WebServlet(name = "PokemonForm", urlPatterns = {"/Pokemon"})
public class PokemonForm extends HttpServlet {

    @EJB
    PBean myBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crear Pokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String ability = request.getParameter("ability");
            int attack = Integer.parseInt(request.getParameter("attack"));
            int defense = Integer.parseInt(request.getParameter("defense"));
            int speed = Integer.parseInt(request.getParameter("speed"));
            int life = Integer.parseInt(request.getParameter("life"));
            String trainer = request.getParameter("trainer");
            Pokemon p = new Pokemon(name, type, ability, attack, defense, speed, life, 0);
            Trainer t = myBean.obtainTrainer(trainer);
            p.setTrainer(t);
            if(myBean.insertPokemon(p)){
                out.println("Pokemon dado de alta.");
            }else{
                out.println("Error, ya hay un pokemon con ese nombre.");
            }
            out.println("<form action=\"index.html\"><input type=\"submit\" value=\"Menu Principal\"></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
