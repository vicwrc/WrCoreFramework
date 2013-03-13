package org.wr.face.common.services.persistence;

import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.edit.input.multiple.MultipleTextInput;
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
        String[] values = 
                MultipleTextInput.mergeValues(
                    MultipleTextInput.parseValues(node.getProperty(attr.getName())), 
                    addedValues, 
                    removedValues);
                
        if (attr.isRequired() && ( values == null || values.length == 0 )) {
            throw new NullPointerException("property \"" + attr.getName() + "\" must be filled!");
        }
        if (!attr.isRequired() && ( values == null || values.length == 0 )) {
            node.removeProperty(attr.getName());
            return;
        }
        if (AttributeType.NUMBER.equals(attr.getType())) {
            node.setProperty(attr.getName(), WrArrays.toIntArray(values));
            return;
        }
        node.setProperty(attr.getName(), values);
    }

}
