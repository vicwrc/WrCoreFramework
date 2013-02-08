package org.wr.face.common.components.view.attribute;

import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class AttributeRow extends WebComponent{

    private final AttributeBean attribute;

    public AttributeRow(AttributeBean attribute) {
        this.attribute = attribute;
        this.setSingleRenderable(false);
    }
    
    
    
    @Override
    public String renderHtml() {
        return "<tr><td>" + getName() +
                "</td><td>"+ attribute.getType().toString() +
                "</td><td>"+ "Default" +
                "</td><td>"+ (attribute.isRequired()?"Yes":"No") +
                "</td><td>"+ String.valueOf(attribute.getMaxEntries()) +
                "</td><td>"+ attribute.getOrder() +
                "</td></tr>";
    }
    
    protected String getName(){
        return "<a href=\"index.jsp?id=" + attribute.getId() + "\">"+attribute.getName()+"</a>";
    }
    
    
}
