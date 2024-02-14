/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils;

/**
 *
 * @author fpw
 */
public class LinkData {
    private final String reference;
    private final String identifier;
    
    public LinkData(String identifier, String reference) {
        this.reference = reference;
        this.identifier = identifier;
    }
    
    public String getReference() {
        return reference;
    }
    
    public String getIdentifier() {
        return identifier;
    }
}
