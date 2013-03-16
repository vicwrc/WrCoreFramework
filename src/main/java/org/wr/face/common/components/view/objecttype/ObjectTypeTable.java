/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.objecttype;

import java.util.List;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.view.TableWithHeader;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class ObjectTypeTable extends TableWithHeader {
    
    protected List<ObjectTypeBean> objectTypes;
    public static String[] attrColumns = new String[]{
        "Name", "Attributes", "Pages", "Order"    
    };
    
    public ObjectTypeTable(List<ObjectTypeBean> objectTypes) {
        super(attrColumns);
        this.objectTypes = objectTypes;
        
        WrCollections.aggregate(objectTypes, new WrCollections.AggregateCondition<ObjectTypeBean, List<WebComponent>>() {
            @Override
            public List<WebComponent> aggregateItem(ObjectTypeBean item, List<WebComponent> currentResult) {
                currentResult.add(new ObjectTypeRow(item));
                return currentResult;
            }
        }, children);
    }
    
    @Override
    public String renderHtml(){
        return "<h3>Object Types</h3>"+super.renderHtml();
    
    }
}
