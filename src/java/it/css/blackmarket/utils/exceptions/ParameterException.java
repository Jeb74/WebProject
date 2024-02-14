/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils.exceptions;

/**
 *
 * @author fpw
 */
public class ParameterException extends RuntimeException{
    
    public ParameterException(String cause) {
        super("ParameterException: " + Messages.getMessageByCause(cause));
    }
}
