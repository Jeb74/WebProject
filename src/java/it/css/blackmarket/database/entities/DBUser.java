/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database.entities;

import it.css.blackmarket.utils.GlobalPaths;
import java.util.HashMap;


/**
 *
 * @author fpw
 */
public class DBUser extends DBEntity{
    
    private static final String USERNAME    = "username";
    private static final String PASSWORD    = "pass";
    private static final String EMAIL       = "email";
    private static final String PHOTO       = "photo";
    
    private final HashMap<String, Object> information;
    
    public DBUser(HashMap<String, Object> information) {
        this.information = information == null ? new HashMap<>() : information;
    }

    @Override
    public Object get(String field) {
        return information.getOrDefault(field, null);
    }
    
    @Override
    public Boolean exists() {
        return !information.isEmpty();
    }
    
    public String getEmail() {
        return (String)get(EMAIL);
    }
    
    public String getPassword() {
        return (String)get(PASSWORD);
    }
    
    public String getUsername() {
        return (String)get(USERNAME);
    }
    
    public String getPhoto() {
        String photo = (String)get(PHOTO);
        if (photo == null && !information.isEmpty()) {
            photo = GlobalPaths.getDefaultPhoto();
        }
        return photo;
    }
    
    public String getRelativePhoto() {
        return GlobalPaths.fromPathGetRelative(getPhoto());
    }
    
    public void setPassword(String password) {
        information.replace(PASSWORD, password);
    }
    
    public void setUsername(String username) {
        information.replace(USERNAME, username);
    }
    
    public void setImage(String image) {
        information.replace(PHOTO, image);
    }
    
}
