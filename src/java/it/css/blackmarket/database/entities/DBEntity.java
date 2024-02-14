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
public abstract class DBEntity {
    
    public abstract Object get(String field);
    
    public abstract Boolean exists();
}
