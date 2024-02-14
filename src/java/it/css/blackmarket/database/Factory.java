/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class Factory {
    
    
    
    protected static final String SELLER          = "seller";
    protected static final String BUYER           = "buyer";
    
    protected static final String ID              = "id";

    protected static final String NAME            = "item_name";
    protected static final String DESCRIPTION     = "item_desc";
    protected static final String QUANTITY        = "item_qty";
    protected static final String PRICE           = "item_price";
    protected static final String IMAGE           = "item_image";
    protected static final String CITY            = "item_city";
    
    
    protected static final String USERNAME        = "username";
    protected static final String PASSWORD        = "pass";
    protected static final String EMAIL           = "email";
    protected static final String PHOTO           = "photo";
    
    protected static final String ITEMS           = "items";
    protected static final String SALES           = "sales";
    protected static final String PURCHASES       = "purchases";
    
    private static final Factory instance = new Factory();
    
    public final DBUserInterface user = new DBUserInterface();
    public final DBProductInterface product = new DBProductInterface();
    public final DBTransactionInterface transaction = new DBTransactionInterface();
    
    private Factory() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static Factory getInstance() {return instance;}
    
    protected static Optional<Connection> establishConnection() {
        String user = "postgres";
        String pass = "fondamenti";
        String db = "jdbc:postgresql://localhost:5432/COMPUTERSCIENCESOCIETY";
        Optional<Connection> conn = Optional.empty();
        try {
            conn = Optional.ofNullable(DriverManager.getConnection(db, user, pass));
        }
        catch (SQLException e) {
            Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }
    
}
