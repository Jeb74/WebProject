/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database;

import it.css.blackmarket.database.entities.DBTransaction;
import it.css.blackmarket.utils.ConnectionCloser;
import it.css.blackmarket.utils.exceptions.SQLConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class DBTransactionInterface implements ConnectionCloser {
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet set = null;
    private HashMap<String, Object> results = null;
    
    protected DBTransactionInterface() {}
    
    public DBTransaction getById(Integer id) {
        results = null;
        String query = "SELECT * FROM Transactions WHERE id = ?";
        
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
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
        return new DBTransaction(results);
    }
    
    public List<DBTransaction> getBySeller(String email) {
        return getByEmail(Factory.SELLER, email);
    }
    
    public List<DBTransaction> getByBuyer(String email) {
        return getByEmail(Factory.BUYER, email);
    }
    
    private List<DBTransaction> getByEmail(String scope, String email) {
        results = null;
        String query = "SELECT * FROM Transactions WHERE " + scope + " = ?";
        List<DBTransaction> list = new LinkedList<>();
        
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            set = stmt.executeQuery();
            
            while (set.next()) {
                results = buildResults();
                list.add(new DBTransaction(results));
                results = null;
            }
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return list;
    }
    
    public DBTransaction getStatisticsFor(String username) {
        results = null;
        String query = "SELECT * FROM statistics WHERE username = ?";
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if (set.next()) {
                results = new HashMap<>();
                results.put(Factory.USERNAME, set.getString(Factory.USERNAME));
                results.put(Factory.ITEMS, set.getInt(Factory.ITEMS));
                results.put(Factory.SALES, set.getInt(Factory.SALES));
                results.put(Factory.PURCHASES, set.getInt(Factory.PURCHASES));
            }
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return new DBTransaction(results);
    }
    
    protected void register(Connection connection, String username) {
        String query1 = "SELECT * FROM products ORDER BY id DESC LIMIT 1";
        String query2 = "SELECT * FROM users WHERE username = ?";
        String query3 = "INSERT INTO Transactions(seller, id) VALUES (?, ?)";
        String email;
        Integer id;
        try {
            conn = connection;
            
            stmt = conn.prepareStatement(query1);
            set = stmt.executeQuery();
            if (set.next()) {
                id = set.getInt(Factory.ID);
                close(stmt, set);
            }
            else throw new RuntimeException("No ID available for new transaction initialization.");
            
            stmt = conn.prepareStatement(query2);
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if (set.next()) {
                email = set.getString(Factory.EMAIL);
                close(stmt, set);
            }
            else throw new RuntimeException("No USER found for new transaction initialization.");
            
            stmt = conn.prepareStatement(query3);
            stmt.setString(1, email);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
    }
    
    public boolean update(Integer id, String username) {
        String query1 = "SELECT * FROM users WHERE username = ?";
        String query2 = "UPDATE transactions SET buyer = ? WHERE id = ? AND buyer IS NULL";
        boolean result = false;
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            
            stmt = conn.prepareStatement(query1);
            stmt.setString(1, username);
            set = stmt.executeQuery();
            
            set.next();
            
            String email = set.getString(Factory.EMAIL);
            close(stmt, set);
            
            stmt = conn.prepareStatement(query2);
            stmt.setString(1, email);
            stmt.setInt(2, id);
            
            
            if(stmt.executeUpdate() > 0) result = true;
            
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return result;
    }
    
    private HashMap<String, Object> buildResults() throws SQLException {
        results = new HashMap<>();
        results.put(Factory.SELLER, set.getString(Factory.SELLER));
        results.put(Factory.BUYER, set.getString(Factory.BUYER));
        results.put(Factory.ID, set.getString(Factory.ID));
        return results;
    }
    
}
