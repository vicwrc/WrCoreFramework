package org.wr.neo4j.meta.model;


import java.util.LinkedList;
import java.util.List;
import org.wr.neo4j.meta.attribute.AttributeType;

/**
 *
 * @author vicwrc
 */
public class AttributeBean extends BaseBean {

    private AttributeType type = AttributeType.TEXT;
    private int maxEntries = 1;
    private boolean required = false;
    private String publicName;
    private List<ObjectTypeBean> objectTypes = new LinkedList<>();
    
    public AttributeBean(long id) {
        super(id);
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<ObjectTypeBean> getObjectTypes() {
        return objectTypes;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

}
