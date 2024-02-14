/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fpw
 */
public class GlobalPaths {
    protected static String GENERIC_PATH = null;
    
    protected static String IMAGE_PATH = "images/";
    protected static String DEFAULT_IMAGE_PATH = "default.png";
    
    protected static String PHOTO_PATH = "propics/";
    protected static String DEFAULT_PHOTO_PATH = "default.png";
    
    protected static String DEFAULT_PHOTO = "./assets/uploads/propics/default.jpeg";
    protected static String DEFAULT_IMAGE = "./assets/uploads/images/default.png";
    
    
    public static void calculateGenericPath(HttpServletRequest request) {
        if (GENERIC_PATH == null) {
            String title = "AlbionOnlineBlackMarket";
            String current_path = request.getServletContext().getRealPath(".");
            int indx = current_path.lastIndexOf(title) + title.length();
            current_path = current_path.substring(0, indx);
            GENERIC_PATH = current_path + "/web/assets/uploads/";
            IMAGE_PATH = GENERIC_PATH + IMAGE_PATH;
            PHOTO_PATH = GENERIC_PATH + PHOTO_PATH;
            DEFAULT_IMAGE_PATH = IMAGE_PATH + DEFAULT_IMAGE_PATH;
            DEFAULT_PHOTO_PATH = PHOTO_PATH + DEFAULT_PHOTO_PATH;
        }
    }
    
    public static String getPhotoPath() {
        return PHOTO_PATH;
    }
    public static String getImagePath() {
        return IMAGE_PATH;
    }
    public static String getDefaultPhotoPath() {
        return DEFAULT_PHOTO_PATH;
    }
    public static String getDefaultImagePath() {
        return DEFAULT_IMAGE_PATH;
    }
    public static String getDefaultPhoto() {
        return DEFAULT_PHOTO;
    }
    public static String getDefaultImage() {
        return DEFAULT_IMAGE;
    }
    public static String fromPathGetRelative(String path) {
        String[] components = path.split("/");
        return "./assets/uploads/" + components[components.length-2] + "/" + components[components.length-1];
    }
            
    
}
