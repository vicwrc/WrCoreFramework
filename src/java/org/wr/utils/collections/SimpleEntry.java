package org.wr.utils.collections;

import java.util.Map;

/**
 *
 * @author Vorontsov
 */
public class SimpleEntry<K,V> implements Map.Entry<K,V>{

    private K k;
    private V v;

    public SimpleEntry(K k, V v) {
        this.k = k;
        this.v = v;
    }
    

    public SimpleEntry() {
    }
    
    
    
    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }

    @Override
    public V setValue(V value) {
        v = value;
        return v;
    }
    
}
