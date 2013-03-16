/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import org.apache.commons.lang.StringUtils;
import org.wr.face.common.components.NestedComponent;

/**
 *
 * @author Vorontsov
 */
public class NestedTableRow extends NestedComponent{

    private final String rowName;
    
    private boolean hidden = false;
    private String id;

    public NestedTableRow(String rowName) {
        this.rowName = rowName;
    }

    public String getRowName() {
        return rowName;
    }
    
    public boolean isHidden() {
        return hidden;
    }

    public NestedTableRow setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }
    
    public String getHidden(){
        return hidden ? " style=\"display: none;\" " : "";
    }

    public String getId() {
        return id;
    }

    public NestedTableRow setId(String id) {
        this.id = id;
        return this;
    }
    
    protected String renderId(){
        return StringUtils.isEmpty(id)?"":" id=\""+id+"\" ";
    }
    
    @Override
    protected String renderStartBlock() {
        return "<tr "+renderId()+getHidden()+"><td width=\"25%\">" + rowName + "</td><td>" ;
    }

    @Override
    protected String renderEndBlock() {
        return "</td></tr>";
    }
    
}
