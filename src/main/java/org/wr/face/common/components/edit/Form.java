/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit;

import org.apache.commons.lang.StringUtils;
import org.wr.face.common.components.NestedComponent;

/**
 *
 * @author Vorontsov
 */
public class Form extends NestedComponent{

    private String actionUrl="";

    public String getActionUrl() {
        return actionUrl;
    }

    public Form setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
        return this;
    }
    
    public String getFormAction(){
        return !StringUtils.isEmpty(actionUrl)? " action=\""+actionUrl+"\"":"";
    }
    
    @Override
    protected String renderStartBlock() {
        return "<form"+getFormAction()+">";
    }

    @Override
    protected String renderEndBlock() {
        return "<input type=\"submit\" value=\"Save\"/></form>";
    }
    
}
