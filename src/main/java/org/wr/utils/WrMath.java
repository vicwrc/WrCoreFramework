/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.utils;

/**
 *
 * @author vorontsov
 */
public class WrMath {
   
    
    private WrMath(){}
    
    public static <T> T nvl(T value, T defValue){
        if(null == value){
            return defValue;
        }
        return value;
    }
        
}
