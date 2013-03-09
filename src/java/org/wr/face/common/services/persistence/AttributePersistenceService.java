package org.wr.face.common.services.persistence;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.wr.neo4j.meta.attribute.AttributeType;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.utils.WrArrays;

/**
 *
 * @author vorontsov
 */
public class AttributePersistenceService {

    public void persist(Node node, AttributeBean attr, String value) {
        if (attr.getMaxEntries() > 1) {
            throw new IllegalArgumentException("property \"" + attr.getName() + "\" must be single!");
        }
        if (attr.isRequired() && StringUtils.isEmpty(value)) {
            throw new NullPointerException("property \"" + attr.getName() + "\" must be filled!");
        }
        if (!attr.isRequired() && StringUtils.isEmpty(value)) {
            node.removeProperty(attr.getName());
        }
        if (AttributeType.NUMBER.equals(attr.getType())) {
            node.setProperty(attr.getName(), new Integer(value).intValue());
        }
        if (AttributeType.TEXT.equals(attr.getType())
                || AttributeType.LIST.equals(attr.getType())
                || AttributeType.DATE.equals(attr.getType())) {
            node.setProperty(attr.getName(), value);
        }
        // todo : add reference logic here
    }

    public void persist(Node node, AttributeBean attr, String[] addedValues, String[] removedValues) {
        if (attr.getMaxEntries() < 2) {
            throw new IllegalArgumentException("property \"" + attr.getName() + "\" must be multiple!");
        }
        String[] values = getValue(attr, getValue(node,attr.getName()), addedValues, removedValues);
        if (attr.isRequired() && values.length == 0) {
            throw new NullPointerException("property \"" + attr.getName() + "\" must be filled!");
        }
        if (!attr.isRequired() && values.length == 0) {
            node.removeProperty(attr.getName());
            return;
        }
        if (AttributeType.NUMBER.equals(attr.getType())) {
            node.setProperty(attr.getName(), toIntArray(values));
            return;
        }
        node.setProperty(attr.getName(), values);
    }

    public Object[] getValue(Node node, String propId) {
        try{
            Object value = node.getProperty(propId);
            if(value instanceof int[]){
                return WrArrays.toStringArray((int[])value);
            }            
            return (Object[])value;
        } catch (NotFoundException e) {
            return new Object[0];
        }
    }
    
    public int[] toIntArray(String[] value) {
        int[] out = new int[value.length];
        for(int i=0; i< value.length; i++) {
            out[i] = Integer.valueOf(value[i]);
        }
        return out;
    }
    
    public String[] getValue(AttributeBean attr, Object[] originalValue, String[] addedValues, String[] removedValues) {
        List<String> values = new LinkedList<>();

        for (Object value : originalValue) {
            if (StringUtils.isEmpty(String.valueOf(value))){
                continue;
            }
            if (!isRemoved(value, removedValues)) {
                values.add(String.valueOf(value));
            }
        }
        for (String addValue : addedValues) {
            if (StringUtils.isEmpty(addValue)) {
                continue;
            }
            values.add(addValue);
        }

        return values.toArray(new String[values.size()]);
    }

    
    
    private boolean isRemoved(Object value, String[] removedValues) {
        for (String delValue : removedValues) {
            if (String.valueOf(value).equals(delValue)) {
                return true;
            }
        }
        return false;
    }
}
