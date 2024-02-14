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
public class DBProduct extends DBEntity{
    
    private static final String ID              = "id";
    private static final String NAME            = "item_name";
    private static final String DESCRIPTION     = "item_desc";
    private static final String QUANTITY        = "item_qty";
    private static final String PRICE           = "item_price";
    private static final String IMAGE           = "item_image";
    protected static final String CITY          = "item_city";

    
    private final HashMap<String, Object> information;
    
    public DBProduct(HashMap<String, Object> information) {
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
    
    public Integer getId() {
        return (Integer)get(ID);
    }
    
    public String getItemName() {
        return (String)get(NAME);
    }
    
    public String getItemDescription() {
        return (String)get(DESCRIPTION);
    }
    
    public Integer getItemQuantity() {
        return (Integer)get(QUANTITY);
    }
    
    public Double getItemPrice() {
        return (Double)get(PRICE);
    }
    
    public String getItemImage() {
        String image = (String)get(IMAGE);
        if (image == null && !information.isEmpty()) {
            image = GlobalPaths.getDefaultImage();
        }
        return image;
    }
    
    public String getRelativeItemImage() {
        return GlobalPaths.fromPathGetRelative(getItemImage());
    }
    
    public String getItemCity() {
        return (String)get(CITY);
    }
    
}
