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
    
    public static String[] EMPTY_STRING_ARRAY = new String[0];
    public static int[] EMPTY_INT_ARRAY = new int[0];
    public static Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    
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
    
    public static String[] toStringArray(Object[] value) {
        String[] out = new String[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = value[i].toString();
        }
        return out;
    }
    
    public static String[] singletonStringArray(Object obj) {
        if(null == obj) {
            return null;
        }
        String[] out = new String[1];
        out[0] = obj.toString();
        return out;
    }
    
    public static String[] singletonStringArray(int obj) {
        String[] out = new String[1];
        out[0] = String.valueOf(obj);
        return out;
    }
    
    
}
