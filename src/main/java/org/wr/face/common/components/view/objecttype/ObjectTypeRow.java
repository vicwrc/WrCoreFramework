package org.wr.face.common.components.view.objecttype;

import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.AttributeBean;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class ObjectTypeRow extends WebComponent {

    protected final ObjectTypeBean bean;

    public ObjectTypeRow(ObjectTypeBean item) {
        this.bean = item;
    }
    
    @Override
    public String renderHtml() {
        return "<tr>"+
                "<td>" +getName(bean) + "</td>" +
                "<td>" +
                    WrCollections.aggregate(bean.getCurrentAttributes(), 
                        new WrCollections.AggregateCondition<AttributeBean, StringBuilder>(){
                            
                            private boolean isFirst = true;
                        @Override
                        public StringBuilder aggregateItem(AttributeBean item, StringBuilder currentResult) {
                            if(isFirst){
                                isFirst = false;
                            } else {
                                currentResult.append("<br>");
                            }
                            currentResult.append(getName(item));
                            return currentResult;
                        }
                    }, new StringBuilder()).toString()
                + "</td>" +
                "<td>" +
                    WrCollections.aggregate(bean.getCurrentPages(), 
                        new WrCollections.AggregateCondition<PageBean, StringBuilder>(){
                            
                            private boolean isFirst = true;
                        @Override
                        public StringBuilder aggregateItem(PageBean item, StringBuilder currentResult) {
                            if(isFirst){
                                isFirst = false;
                            } else {
                                currentResult.append("<br>");
                            }
                            currentResult.append(getName(item));
                            return currentResult;
                        }
                    }, new StringBuilder()).toString()
                + "</td>" +
                "<td>" +bean.getOrder() + "</td>" +
                "</tr>";
    }
    
    
    protected String getName(BaseBean bean){
        return "<a href=\"index.jsp?id=" + bean.getId() + "\">"+bean.getName()+"</a>";
    }
}
