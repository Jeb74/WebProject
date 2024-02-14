/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.database;

import it.css.blackmarket.database.entities.DBProduct;
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
public class DBProductInterface implements ConnectionCloser {
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet set = null;
    private HashMap<String, Object> results = null;
    
    protected DBProductInterface() {}
    
    public DBProduct getById(Integer id) {
        results = null;
        String query = "SELECT * FROM Products WHERE id = ?";
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, id.toString());
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
        return new DBProduct(results);
    }
    
    public List<DBProduct> getByIds(List<Integer> ids) {
        List<DBProduct> ps = new LinkedList<>();
        for (Integer i : ids) {
            ps.add(getById(i));
        }
        return ps;
    }
    
    public DBProduct getByOffset(String username, int offset) {
        results = null;
        String query1 = "SELECT COUNT(*) AS total FROM display WHERE seller != ?";
        String query2 = "SELECT * FROM display WHERE seller != ? LIMIT 1 OFFSET ?";
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            
            stmt = conn.prepareStatement(query1);
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if (set.next()) {
                int total = set.getInt("total");
                if (offset >= total && total > 0) offset = total-1;
            }
            
            stmt = conn.prepareStatement(query2);
            stmt.setString(1, username);
            stmt.setInt(2, offset);
            set = stmt.executeQuery();
            
            if (set.next()) {
                results = buildResults();
                results.put(Factory.SELLER, set.getString(Factory.SELLER));
                results.put("offset", offset);
            }
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
        return new DBProduct(results);
    }
    
    public void register(String in, String id, Integer qty, Double price, String city, String img, String username) {
        try {
            conn = Factory.establishConnection().orElseThrow(SQLConnectionException::new);
            stmt = conn.prepareStatement(
                    "INSERT INTO Products(item_name, item_desc, item_qty, item_price, item_city, item_image) "
                            + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, in);
            stmt.setString(2, id);
            stmt.setInt(3, qty);
            stmt.setDouble(4, price);
            stmt.setString(5, city);
            stmt.setString(6, img);
            stmt.executeUpdate();
            Factory.getInstance().transaction.register(conn, username);
        }
        catch (SQLException | SQLConnectionException e) {
            Logger.getLogger(DBUserInterface.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            close(set, stmt, conn);
        }
    }
    
    private HashMap<String, Object> buildResults() throws SQLException {
        results = new HashMap<>();
        results.put(Factory.ID, set.getInt(Factory.ID));
        results.put(Factory.NAME, set.getString(Factory.NAME));
        results.put(Factory.DESCRIPTION, set.getString(Factory.DESCRIPTION));
        results.put(Factory.QUANTITY, set.getInt(Factory.QUANTITY));
        results.put(Factory.PRICE, set.getDouble(Factory.PRICE));
        results.put(Factory.CITY, set.getString(Factory.CITY));
        results.put(Factory.IMAGE, set.getString(Factory.IMAGE));
        return results;
    }
    
}
