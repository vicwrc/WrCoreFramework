/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.page;

import org.wr.face.common.components.WebComponent;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public class PageRow extends WebComponent{

    private final PageBean bean;

    public PageRow(PageBean bean) {
        this.bean = bean;
    }
    
    @Override
    public String renderHtml() {
       return "<tr><td>" + getName() + 
               "</td><td>"+ bean.getAction() +
               "</td><td>"+ bean.getOrder() +
               "</td></tr>";
    }
    
    protected String getName(){
        return "<a href=\"index.jsp?id=" + bean.getId() + "\">"+bean.getName()+"</a>";
    }
}
