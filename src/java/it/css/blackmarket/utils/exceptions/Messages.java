/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils.exceptions;

/**
 *
 * @author fpw
 */
public enum Messages {

    EMAIL_LOGIN("Email Not Registered", "The email is not registered."),
    EMAIL_FORMAT("Email Format", "The email is not correctly formatted."),
    EMAIL_REGISTERED("Email Registered", "The email is already associated to an account."),
    
    PASSWORD_WRONG("Password Wrong", "This password is wrong."),
    PASSWORD_WEAK("Password Weak", "This password is too weak."),
    PASSWORD_NOT_IDENTICAL("Password Not Identical", "You confirmation password is not identical to the first one."),
    
    USERNAME_REGISTERED("Username Registered", "The username you provided is already registered."),
    USERNAME_FORMAT("Username Format", "The username you provided is not correctly formatted."),
    
    ITEM_NAME("Item Name", "The name of the item you provided is incorrect."),
    ITEM_DESCRIPTION("Item Description", "The length of your description is too short or too long."),
    ITEM_QUANTITY("Item Quantity", "The quantity of item you provided is not correct.\nInsert a number between 1 and 999."),
    ITEM_PRICE("Item Price", "The price you chose is lower than 0.50€."),
    ITEM_CITY("Item City", "The city you provided is not correct.");

    private final String cause;
    private final String message;

    private Messages(String cause, String message) {
        this.cause = cause;
        this.message = message;
    }

    protected static String getMessageByCause(String cause) {
        for (Messages m : Messages.values()) {
            if (m.cause.equals(cause)) {
                return m.message;
            }
        }
        return "";
    }
    
    public String getCause() {
        return cause;
    }
    
}
