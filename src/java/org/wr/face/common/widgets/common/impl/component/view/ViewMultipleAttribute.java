/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common.impl.component.view;

import org.neo4j.graphdb.Node;
import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ViewMultipleAttribute extends WebComponent{

    private final AttributeBean attr;
    private final Node node;
    
    public ViewMultipleAttribute(AttributeBean attr, Node node) {
        this.attr = attr;
        this.node = node;        
    }

    @Override
    public String renderHtml() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String value : getValues(node.getProperty(attr.getName()))){
            if(isFirst) {
                isFirst = false;
            }else{
                sb.append("<br>");
            }
            sb.append(value);
        }
        
        return sb.toString();
    }
    
    public String[] getValues(Object o){
        if(o instanceof String[]) {
            return (String[])o;
        }
        if(o instanceof int[]){
            return toStringArray((int[])o);
        }
        return null;
    }
    
    public static String[] toStringArray(int[] value) {
        String[] out = new String[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = String.valueOf(value[i]);
        }
        return out;
    }
}
