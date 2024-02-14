/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database;

import it.css.blackmarket.database.entities.DBUser;
import it.css.blackmarket.utils.ConnectionCloser;
import it.css.blackmarket.utils.exceptions.SQLConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class DBUserInterface implements ConnectionCloser {
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet set = null;
    private HashMap<String, Object> results = null;
    
    protected DBUserInterface() {}
    
    public DBUser getByUsername(String username) {
        String query = "SELECT * FROM Users WHERE username = ?";
        return performQuery(query, username);
    }
    
    public DBUser getByEmail(String email) {
        String query = "SELECT * FROM Users WHERE email = ?";
        return performQuery(query, email);
    }
    
    public DBUser getByLogin(String email, String password) {
        String query = "SELECT * FROM Users WHERE email = ? AND pass = ?";
        return performQuery(query, email, password);
    }
    
    public boolean exists(String field, String value) {
        boolean result = false;
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement("SELECT * FROM users WHERE # = ?".replace("#", field));
            stmt.setString(1, value);
            set = stmt.executeQuery();
            result = set.next();
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return result;
    }
    
    public void register(String email, String username, String pass, String photo) {
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement("INSERT INTO users(email, username, pass, photo) VALUES (?, ?, ?, ?)");
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3, pass);
            stmt.setString(4, photo);
            stmt.executeUpdate();
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
    }
    
    private DBUser performQuery(String query, String... arguments) {
        results = null;
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(query);
            for (int i = 0; i < arguments.length; i++) {
                stmt.setString(i+1, arguments[i]);
            }
            set = stmt.executeQuery();
            
            if (set.next()) {
                results = buildResults();
            }
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return new DBUser(results);
    }
    
    private HashMap<String, Object> buildResults() throws SQLException {
        results = new HashMap<>();
        results.put(Factory.USERNAME, set.getString(Factory.USERNAME));
        results.put(Factory.EMAIL, set.getString(Factory.EMAIL));
        results.put(Factory.PASSWORD, set.getString(Factory.PASSWORD));
        results.put(Factory.PHOTO, set.getString(Factory.PHOTO));
        return results;
    }
    
}
