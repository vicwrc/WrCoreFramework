/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.conditions;

import org.wr.face.common.components.WebComponent;
import org.wr.utils.collections.WrCollections.AggregateCondition;

/**
 *
 * @author vorontsov
 */
public class ComponentJoinCondition implements AggregateCondition<WebComponent, StringBuilder> {

    private static ComponentJoinCondition INSTANCE = new ComponentJoinCondition();

    public static ComponentJoinCondition getInstance() {
        return INSTANCE;
    }  
    
    
    @Override
    public StringBuilder aggregateItem(WebComponent item, StringBuilder currentResult) {
        return currentResult.append(item.renderHtml());
    }
    
}
