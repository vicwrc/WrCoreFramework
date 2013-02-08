package org.wr.face.common.components.view;

import org.wr.utils.collections.WrCollections;
import org.wr.utils.collections.WrCollections.AggregateCondition;

/**
 *
 * @author vorontsov
 */
public class TableWithHeader extends SimpleTableComponent{

    protected final String[] columns;
    
    private static TableColumnCreator tableColumnCreator = new TableColumnCreator();

    public TableWithHeader(String... columns) {
        this.columns = columns;
    }

    public static TableColumnCreator getTableColumnCreator() {
        return tableColumnCreator;
    } 
    
    protected String renderHeaderColumns(){
        return WrCollections.aggregate(columns,tableColumnCreator, new StringBuilder()).toString();
    }
    
    @Override
    protected String renderStartBlock() {
       return "<table class=\"bordered\"><thead><tr>" + renderHeaderColumns() + "</tr></thead><tbody>";
    }
    
    public static class TableColumnCreator implements AggregateCondition<String, StringBuilder>{
    
        @Override
        public StringBuilder aggregateItem(String item, StringBuilder currentResult) {
            return currentResult.append("<td>").append(item).append("</td>");
        }
    }
 
}
