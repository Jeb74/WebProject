/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils.exceptions;

/**
 *
 * @author fpw
 */
public class SQLConnectionException extends RuntimeException {
    public SQLConnectionException() {
        super("Connection error to DataBase.");
    }
}
