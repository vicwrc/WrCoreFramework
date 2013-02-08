/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import org.wr.face.common.components.WebComponent;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class TableRow extends WebComponent{

    protected final String[] columns;

    public TableRow(String[] columns) {
        this.columns = columns;
        this.setSingleRenderable(false);
    }
    
    @Override
    public String renderHtml() {
        return "<tr>" + WrCollections.aggregate(columns,TableWithHeader.getTableColumnCreator(), new StringBuilder()).toString() + "</tr>";
    }
    
}
