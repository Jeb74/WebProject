/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils;

/**
 *
 * @author fpw
 */
public interface Verifications {
    static final int USERNAME_LENGTH = 30; 
    static final int PASSWORD_LENGTH = 30; 
    static final int ITEM_NAME_LENGTH = 30;
    static final int ITEM_DESCRIPTION_LENGTH = 150;
    static final int MAX_ITEM_QTY = 999;
    static final int ITEM_NAME_MIN = 4;
    static final int ITEM_DESCRIPTION_MIN = ITEM_NAME_MIN;
    
    static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{1,30}$";
    static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,30}";
    static final String PASSWORD_ALTERNATIVE_PATTERN = "[0-9]{5}";
    static final String ITEM_NAME_PATTERN = "[\\w'\\s]{" + ITEM_NAME_MIN + "," + ITEM_NAME_LENGTH + "}";
    static final String ITEM_DESCRIPTION_PATTERN = ".{" + ITEM_DESCRIPTION_MIN + "," + ITEM_DESCRIPTION_LENGTH + "}";
    static final String ITEM_CITY_PATTERN = "(?=.*[a-zA-Z])[A-Za-z\\s]{2,20}";
}
