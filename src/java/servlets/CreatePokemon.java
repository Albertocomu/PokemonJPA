/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.PBean;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "CreatePokemon", urlPatterns = {"/CreatePokemon"})
public class CreatePokemon extends HttpServlet {

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
            out.println("<title>Servlet CreatePokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Create Pokemon</h1>");
            out.println("<form action=\"Pokemon\" method =\"GET\">");
            out.println("Nombre: <input type=\"text\" name=\"name\">");
            out.println("Tipo: <input type=\"text\" name=\"type\">");
            out.println("Habilidad: <input type=\"text\" name=\"ability\">");
            out.println("Ataque: <input type=\"number\" name=\"attack\">");
            out.println("Defensa: <input type=\"number\" name=\"defense\">");
            out.println("Rapidez: <input type=\"number\" name=\"speed\">");
            out.println("Salud: <input type=\"number\" name=\"life\">");
            out.println("Entrenador: <select name=\"trainer\">");
            try{
                List<Trainer> trainers = myBean.selectTrainersWithPokemons();
                for(Trainer t : trainers){
                    out.println("<option value="+t.getName()+">"+t.getName()+"</option>");
                }
            }catch(Exception error){
                error.printStackTrace();
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Create\">");
            out.println("</form>");
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
