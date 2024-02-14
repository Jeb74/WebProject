/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.css.blackmarket.servlet;

import it.css.blackmarket.database.Factory;
import it.css.blackmarket.utils.LinkData;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fpw
 */
@WebServlet(name = "Purchase", urlPatterns = {"/purchase"})
public class PurchaseServlet extends HttpServlet {

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
        String path = "success.jsp";
        String message = "Your purchase ended successfully.\nFeel free to use the following links to go back to our pages.";
        try {
            String username = (String) request.getSession().getAttribute("username");
            int id = Integer.parseInt(request.getParameter("id"));
            if (id < 0 || !Factory.getInstance().transaction.update(id, username)) {
                throw new RuntimeException();
            }
        }
        catch(RuntimeException e) {
            path = "error.jsp";
            message = "The item you were trying to purchase could not be available anymore." +
                    "\nFeel free to use the following links to go back to our pages.";
        }
        buildRequest(request, message);
        request.getRequestDispatcher(path).forward(request, response);
    }
    
    private void buildRequest(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        request.setAttribute("links", getBindedLinks());
    }
    
    private ArrayList<LinkData> getBindedLinks() {
        ArrayList<LinkData> lnks = new ArrayList<>();
        lnks.add(new LinkData("Main Page", "index.jsp"));
        lnks.add(new LinkData("Go back to the list of items", "products.jsp"));
        lnks.add(new LinkData("Become a seller", "selling.jsp"));
        return lnks;
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
