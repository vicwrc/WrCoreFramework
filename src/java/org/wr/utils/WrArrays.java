/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.utils;

/**
 *
 * @author vorontsov
 */
public class WrArrays {
    
    private WrArrays(){}
    
    public static int[] toIntArray(String[] value) {
        int[] out = new int[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = Integer.valueOf(value[i]);
        }
        return out;
    }
    
    public static String[] toStringArray(int[] value) {
        String[] out = new String[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = String.valueOf(value[i]);
        }
        return out;
    }
    
}
