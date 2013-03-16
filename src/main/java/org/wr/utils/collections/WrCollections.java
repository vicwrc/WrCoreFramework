/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.utils.collections;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author vorontsov
 */
public class WrCollections {

    public interface FilterCondition<T>{
        
        boolean isAppropriate(T element);
        
    }
    
    public interface AggregateCondition<T,V>{
        
        V aggregateItem(T item, V currentResult);
        
    }
    
    public interface ObserveCondition<T>{
        
        void observe(T item);
        
    }
    
    public static <T> Collection<T> filter(Iterable<T> source, FilterCondition<T> condition){
        Collection<T> newCollection = new LinkedList<>();
        for(T item : source){
            if(condition.isAppropriate(item)) {
                newCollection.add(item);
            }
        }
        return newCollection;
    }
    
    public static <V,T> T aggregate(V[] source,AggregateCondition<V,T> condition, T startValue) {
        T curResult = startValue;
        for(V item : source){
           curResult = condition.aggregateItem(item, curResult);
        }
        return curResult;
    }
    
    public static <V,T> T aggregate(Iterable<V> source,AggregateCondition<V,T> condition, T startValue) {
        T curResult = startValue;
        for(V item : source){
           curResult = condition.aggregateItem(item, curResult);
        }
        return curResult;
    }
    
    public static <T> void observe(Iterable<T> source,ObserveCondition<T> condition){
        for(T item : source){
            condition.observe(item);
        }
    }
    
    
    
}
