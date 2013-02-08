/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.model.page;

import java.util.List;
import org.wr.neo4j.meta.model.PageBean;
import org.wr.neo4j.meta.model.WidgetBean;

/**
 *
 * @author vorontsov
 */
public class RedirectPageBean extends PageBean{

    public static String PROCESS_CLASS_NAME = "org.wr.face.common.page.handlers.RedirectPageHandler";
    
    private String pageToRedirect = "";
    
    public RedirectPageBean(long id, String action) {
        super(id, action);
        this.setProcessClass(PROCESS_CLASS_NAME);
    }

    public RedirectPageBean(long id, String action, String name, String color, String extraParams, boolean hidden, List<WidgetBean> widgets,String pageToRedirect) {
        super(id, action, name, PROCESS_CLASS_NAME, color, extraParams, hidden, widgets);
        this.pageToRedirect = pageToRedirect;
    }

    public String getPageToRedirect() {
        return pageToRedirect;
    }

    public void setPageToRedirect(String pageToRedirect) {
        this.pageToRedirect = pageToRedirect;
    }
    
}
