package org.wr.neo4j.meta.model.attribute;

import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class ReferenceAttribute  extends AttributeBean{

    private String referenceTo;    
    
    public ReferenceAttribute(long id) {
        super(id);
    }

    public String getReferenceTo() {
        return referenceTo;
    }

    public void setReferenceTo(String referenceTo) {
        this.referenceTo = referenceTo;
    }
    
    
    
}
