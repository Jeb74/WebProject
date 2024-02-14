/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils.exceptions;

import it.css.blackmarket.utils.LinkData;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fpw
 */
public class AccessException extends RuntimeException {
    
    private static final String MESSAGE = "AccessException: You tried to access to # without being logged-in.";
    private static final ArrayList<LinkData> LINKS = setLinks();
    
    public AccessException() {
        super(MESSAGE);
    }
    
    private static ArrayList<LinkData> setLinks() {
        ArrayList<LinkData> lnks = new ArrayList<>();
        lnks.add(new LinkData("Log-in to access this section", "login.jsp"));
        lnks.add(new LinkData("Register an account", "register.jsp"));
        lnks.add(new LinkData("Go back to the main page", "index.jsp"));
        return lnks;
    }
    
    public static void raiseIllegalAccessError(String context,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("links", LINKS);
        session.setAttribute("message", setContext(context));
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    
    public static ArrayList<LinkData> getLinks() {
        return LINKS;
    }
    
    public static String setContext(String context) {
        return MESSAGE.replace("#", context);
    }
    
}
