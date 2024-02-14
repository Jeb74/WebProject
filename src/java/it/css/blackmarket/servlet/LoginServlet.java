/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.css.blackmarket.servlet;

import it.css.blackmarket.database.Factory;
import java.io.IOException;
import it.css.blackmarket.database.entities.DBUser;
import it.css.blackmarket.utils.GlobalPaths;
import it.css.blackmarket.utils.LinkData;
import it.css.blackmarket.utils.Variable;
import it.css.blackmarket.utils.Verifications;
import it.css.blackmarket.utils.exceptions.Messages;
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
@WebServlet(name = "Access", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet implements Verifications {
    
    
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
        GlobalPaths.calculateGenericPath(request);
        String path = "error.jsp";
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            DBUser user = verify(email, password);
            buildRequest(request, "Please, use the following links to go back to our pages:");
            path = "personal_area.jsp";
            request.getSession().setAttribute("username", user.getUsername());
        }
        catch (Exception e) {
            buildRequest(request, e.getMessage());
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    
    private DBUser verify(String email, String password) {
        Factory factory = Factory.getInstance();
        DBUser user = factory.user.getByEmail(email);
        
        Variable.verify(Messages.EMAIL_FORMAT.getCause(), () -> {
            return email.matches(EMAIL_PATTERN);
        });
        Variable.verify(Messages.EMAIL_LOGIN.getCause(), () -> {
            return user.exists();
        });
        Variable.verify(Messages.PASSWORD_WEAK.getCause(), () -> {
            return password.matches(PASSWORD_PATTERN) || password.matches(PASSWORD_ALTERNATIVE_PATTERN);
        });
        Variable.verify(Messages.PASSWORD_WRONG.getCause(), () -> {
            return password.equals(user.getPassword());
        });
        return user;
    }
    
    private void buildRequest(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        request.setAttribute("links", getBindedLinks());
    }
    
    private ArrayList<LinkData> getBindedLinks() {
        ArrayList<LinkData> lnks = new ArrayList<>();
        lnks.add(new LinkData("Main Page", "index.jsp"));
        lnks.add(new LinkData("Start selling", "selling.jsp"));
        lnks.add(new LinkData("Get a look at the list of buyable items", "products.jsp"));
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
