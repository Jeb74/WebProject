/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.css.blackmarket.servlet;

import it.css.blackmarket.database.Factory;
import it.css.blackmarket.utils.GlobalPaths;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.css.blackmarket.utils.LinkData;
import it.css.blackmarket.utils.Variable;
import it.css.blackmarket.utils.Verifications;
import it.css.blackmarket.utils.exceptions.AccessException;
import it.css.blackmarket.utils.exceptions.Messages;
import it.css.blackmarket.utils.exceptions.ParameterException;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author fpw
 */
@WebServlet(name = "Products", urlPatterns = {"/new-product"})
@MultipartConfig
public class ProductServlet extends HttpServlet implements Verifications {

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
            final String username = (String) request.getSession().getAttribute("username");
            final String name = request.getParameter("item_name");
            final String desc = request.getParameter("item_description");
            final Integer qty = Integer.valueOf(request.getParameter("item_quantity"));
            final Double price = Double.valueOf(request.getParameter("item_price"));
            final String city = request.getParameter("item_city");
            final String img = handleImage(request);
            
            verify(username, name, desc, qty, price, city);
            Factory factory = Factory.getInstance();
            factory.product.register(name, desc, qty, price, city, img, username);
            buildRequest(request, "Please, use the following links to go back to our pages:");
            path = "success.jsp";
        }
        catch(ParameterException e) {
            buildRequest(request, e.getMessage());
        }
        catch(AccessException e) {
            buildRequest(request, AccessException.setContext("product upload section"), AccessException.getLinks());
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    
    private void verify(String un, String name, String desc, int qty, double price, String city) {
        if (un == null) {
            throw new AccessException();
        }
        Variable.verify(Messages.ITEM_NAME.getCause(), () -> {
            return name.matches(ITEM_NAME_PATTERN);
        });
        Variable.verify(Messages.ITEM_DESCRIPTION.getCause(), () -> {
            return desc.matches(ITEM_DESCRIPTION_PATTERN);
        });
        Variable.verify(Messages.ITEM_QUANTITY.getCause(), () -> {
            return qty > 0 && qty <= MAX_ITEM_QTY;
        });
        Variable.verify(Messages.ITEM_PRICE.getCause(), () -> {
            return price >= 0.50D;
        });
        Variable.verify(Messages.ITEM_CITY.getCause(), () -> {
            return city.matches(ITEM_CITY_PATTERN);
        });
    }
    
    private String handleImage(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart("item_image");
        String path = null;
        try {
            InputStream inputFile = part.getInputStream();
            String file_name = part.getSubmittedFileName();
            int indx = file_name.lastIndexOf(".");
            String new_name = UUID.randomUUID() + file_name.substring(indx);
            path = GlobalPaths.getImagePath() + new_name;
            File img = new File(path);
            Files.copy(inputFile, img.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e) {
            path = GlobalPaths.getDefaultImagePath();
        }
        return path;
    }
    
    private void buildRequest(HttpServletRequest request, String message) {
        request.setAttribute("message", message);
        request.setAttribute("links", getBindedLinks());
    }
    
    private void buildRequest(HttpServletRequest request, String message, ArrayList<LinkData> links) {
        request.setAttribute("message", message);
        request.setAttribute("links", links);
    }
    
    private ArrayList<LinkData> getBindedLinks() {
        ArrayList<LinkData> lnks = new ArrayList<>();
        lnks.add(new LinkData("Main Page", "index.jsp"));
        lnks.add(new LinkData("Add an item to the shop", "selling.jsp"));
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
