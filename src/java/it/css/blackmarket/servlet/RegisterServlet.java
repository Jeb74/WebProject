/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.css.blackmarket.servlet;

import it.css.blackmarket.database.Factory;
import static it.css.blackmarket.servlet.LoginServlet.EMAIL_PATTERN;
import static it.css.blackmarket.servlet.LoginServlet.PASSWORD_PATTERN;
import it.css.blackmarket.utils.GlobalPaths;
import it.css.blackmarket.utils.LinkData;
import it.css.blackmarket.utils.Variable;
import it.css.blackmarket.utils.Verifications;
import it.css.blackmarket.utils.exceptions.Messages;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author fpw
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet implements Verifications {

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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmation = request.getParameter("password_repetition");
            String photo = handleImage(request);
            
            verify(email, password, confirmation, username);
            Factory factory = Factory.getInstance();
            factory.user.register(email, username, password, photo);
            request.getSession().setAttribute("username", factory.user.getByEmail(email).getUsername());

            
            buildRequest(request, "Please, use the following links to go back to our pages:");
            path = "success.jsp";
        }
        catch (Exception e) {
            buildRequest(request, e.getMessage());
        }
        request.getRequestDispatcher(path).forward(request, response);
        
    }
    
    private void verify(String email, String password, String confirmation, String username) {
        Factory factory = Factory.getInstance();
        
        Variable.verify(Messages.EMAIL_FORMAT.getCause(), () -> {
            return email.matches(EMAIL_PATTERN);
        });
        Variable.verify(Messages.EMAIL_REGISTERED.getCause(), () -> {
            return !factory.user.exists("email", email);
        });
        
        Variable.verify(Messages.PASSWORD_WEAK.getCause(), () -> {
            return password.matches(PASSWORD_PATTERN);
        });
        Variable.verify(Messages.PASSWORD_NOT_IDENTICAL.getCause(), () -> {
            return password.equals(confirmation);
        });
        
        Variable.verify(Messages.USERNAME_FORMAT.getCause(), () -> {
            return username.matches(USERNAME_PATTERN);
        });
        Variable.verify(Messages.USERNAME_REGISTERED.getCause(), () -> {
            return !factory.user.exists("username", username);
        });
    }

    private String handleImage(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart("photo");
        String path = GlobalPaths.getDefaultPhotoPath();
        if (part != null) {
            InputStream inputFile = part.getInputStream();
            String file_name = part.getSubmittedFileName();
            int indx = file_name.lastIndexOf(".");
            String new_name = UUID.randomUUID() + file_name.substring(indx);
            path = GlobalPaths.getPhotoPath() + new_name;
            File img = new File(path);
            Files.copy(inputFile, img.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return path;
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
        lnks.add(new LinkData("See your personal statistics", "personal_area.jsp"));
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
