/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils;

/**
 *
 * @author fpw
 */
public interface ConnectionCloser {
    
    default <C extends AutoCloseable> void close(C... closeables) {
        for (C c : closeables) {
            if (c != null) {
                try {
                    c.close();
                }
                catch (Exception ignored) {}
            }
        }
    }
    
}
