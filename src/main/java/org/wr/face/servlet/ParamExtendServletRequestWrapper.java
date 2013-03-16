/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.servlet;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.wr.utils.web.CommonHttpServletRequestWrapper;

/**
 *
 * @author vorontsov
 */
public class ParamExtendServletRequestWrapper extends CommonHttpServletRequestWrapper {

    private Map<Class, ValueParser> parsers = new HashMap<>();
            
            
    public ParamExtendServletRequestWrapper(HttpServletRequest request) {
        super(request);
        initParsers();
    }

    public Map<Class, ValueParser> getParsers() {
        return parsers;
    }
    
    public <T> T getParameter(String name, Class<T> clazz) {
        if(String.class.equals(clazz)){
            return (T)request.getParameter(name);
        }
        ValueParser<T> parser = parsers.get(clazz); 
        if(parser == null){
            throw new RuntimeException("parser not found for class :"+clazz);
        }
        return parser.getParameter(name, request);
    }
    
    public <T> T[] getParameterValues(String name, Class<T> clazz) {
        if(String.class.equals(clazz)){
            return (T[])request.getParameterValues(name);
        }
        ValueParser<T> parser = parsers.get(clazz); 
        if(parser == null){
            throw new RuntimeException("parser not found for class :"+clazz);
        }
        return parser.getParameterValues(name, request);
    }
    
    private void initParsers(){
        parsers.put(Long.class, new ValueParser<Long>(){

            @Override
            protected Long parseNonEmptyValue(String value) {
                return Long.valueOf(value);
            }

            @Override
            protected Long[] arrayCreation(int length) {
                return new Long[length];
            }

           
        });
        
        parsers.put(Integer.class, new ValueParser<Integer>(){

            @Override
            protected Integer parseNonEmptyValue(String value) {
                return Integer.valueOf(value);
            }

            @Override
            protected Integer[] arrayCreation(int length) {
                return new Integer[length];
            }
        });
    
        parsers.put(Boolean.class, new ValueParser<Boolean>(){

            @Override
            protected Boolean parseNonEmptyValue(String value) {
                if("YES".equalsIgnoreCase(value) || "TRUE".equalsIgnoreCase(value)){
                    return true;
                }
                return false;
            }

            @Override
            protected Boolean[] arrayCreation(int length) {
                return new Boolean[length];
            }

            
        });
    }

    public abstract static class ValueParser<T> {

        public T getParameter(String name, HttpServletRequest request) {
            if (!StringUtils.isEmpty(name)) {
                return parseValue(request.getParameter(name));
            }
            return null;
        }
        
        public T[] getParameterValues(String name, HttpServletRequest request) {
            if (!StringUtils.isEmpty(name)) {
                String[] strValues = request.getParameterValues(name);
                T[] values = arrayCreation(strValues.length);
                for(int i= 0; i< values.length;i++){
                    values[i] = parseValue(strValues[i]);
                }
                return values;
            }
            return null;
        }

        protected Object getValue(String name, HttpServletRequest request) {
            String[] values = request.getParameterValues(name);
            if (values.length == 1) {
                return values[0];
            }
            return values;
        }

        protected T parseValue(String value){
            if(StringUtils.isEmpty(value) || "null".equalsIgnoreCase(value)){
                return null;
            }
            return parseNonEmptyValue(value);
        }
        
        protected abstract T parseNonEmptyValue(String value);
        
        protected abstract T[] arrayCreation(int length);
    }
}
