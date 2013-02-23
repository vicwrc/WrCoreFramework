/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.page;

import java.util.List;
import org.wr.face.common.components.WebComponent;
import org.wr.face.common.components.view.TableWithHeader;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class PageTable extends TableWithHeader{
    
    protected List<PageBean> pages;
    public static String[] attrColumns = new String[]{
        "Name", "Action", "Order"    
    };
    
    public PageTable(List<PageBean> pages) {
        super(attrColumns);
        this.pages = pages;
        
        WrCollections.aggregate(pages, new WrCollections.AggregateCondition<PageBean, List<WebComponent>>() {
            @Override
            public List<WebComponent> aggregateItem(PageBean item, List<WebComponent> currentResult) {
                currentResult.add(new PageRow(item));
                return currentResult;
            }
        }, children);
    }
    
    @Override
    public String renderHtml(){
        return "<h3>Pages</h3>"+super.renderHtml();
    
    }
}
