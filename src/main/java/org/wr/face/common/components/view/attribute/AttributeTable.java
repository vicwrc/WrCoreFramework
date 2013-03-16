/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.attribute;

import java.util.List;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.view.TableWithHeader;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class AttributeTable extends TableWithHeader {
    
    protected List<AttributeBean> attributes;
    public static String[] attrColumns = new String[]{
        "Name", "Type", "Group", "Required", "Max Entries", "Order"    
    };
    
    public AttributeTable(List<AttributeBean> attributes) {
        super(attrColumns);
        this.attributes = attributes;
        
        WrCollections.aggregate(attributes, new WrCollections.AggregateCondition<AttributeBean, List<WebComponent>>() {
            @Override
            public List<WebComponent> aggregateItem(AttributeBean item, List<WebComponent> currentResult) {
                currentResult.add(new AttributeRow(item));
                return currentResult;
            }
        }, children);
    }
    
    @Override
    public String renderHtml(){
        return "<h3>Attributes</h3>"+super.renderHtml();
    
    }
}
