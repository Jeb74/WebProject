/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.css.blackmarket.utils;

import it.css.blackmarket.utils.exceptions.ParameterException;
import java.util.function.Supplier;


/**
 *
 * @author fpw
 */
public interface Variable {
    
    static void verify(String cause, Supplier<Boolean> f) {
        if (!f.get()) {
            throw new ParameterException(cause);
        }
    };
    
}
