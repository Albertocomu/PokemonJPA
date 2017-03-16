/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.PBean;
import entities.Pokemon;
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
@WebServlet(name = "PokemonList", urlPatterns = {"/PokemonList"})
public class PokemonList extends HttpServlet {

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
            out.println("<title>Listado de Pokemons</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de Pokemons</h1>");
            List<Pokemon> pokemons = myBean.selectAllPokemonsInOrder();
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Pokemon</th>");
            out.println("<th>Entrenador</th>");
            out.println("<th>Acción</th>");
            out.println("</tr>");
            for(Pokemon p : pokemons){
                out.println("<form action=\"DeletePokemon\" method=\"GET\">");
                out.println("<tr>");
                out.println("<td>"+p.getName()+", Nivel "+p.getLevel()+", Vida: "+p.getLife()+"</td>");
                out.println("<td>"+p.getTrainer().getName()+"</td>");
                out.println("<td><input type=\"submit\" value=\"Eliminar\">");
                out.println("<input type=\"hidden\" name=\"name\" value=\""+p.getName()+"\"></td>");
                out.println("</tr>");
                out.println("</form>");
            }
            out.println("</table>");
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
