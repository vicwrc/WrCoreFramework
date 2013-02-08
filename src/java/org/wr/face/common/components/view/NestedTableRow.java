/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import org.wr.face.common.components.NestedComponent;

/**
 *
 * @author Vorontsov
 */
public class NestedTableRow extends NestedComponent{

    private final String rowName;

    public NestedTableRow(String rowName) {
        this.rowName = rowName;
    }

    public String getRowName() {
        return rowName;
    }
    
    @Override
    protected String renderStartBlock() {
        return "<tr><td width=\"25%\">" + rowName + "</td><td>" ;
    }

    @Override
    protected String renderEndBlock() {
        return "</td></tr>";
    }
    
}
