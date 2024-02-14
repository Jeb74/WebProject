/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database.entities;

import java.util.HashMap;

/**
 *
 * @author fpw
 */
public class DBTransaction extends DBEntity{
    
    private static final String SELLER          = "seller";
    private static final String ID              = "id";
    private static final String BUYER           = "buyer";
    
    private final HashMap<String, Object> information;
    
    public DBTransaction(HashMap<String, Object> information) {
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
    
    public String getSeller() {
        return (String)get(SELLER);
    }
    
    public Integer getId() {
        return (Integer)get(ID);
    }
    
    public String getBuyer() {
        return (String)get(BUYER);
    }
    
}
