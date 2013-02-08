/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit;

import java.util.HashMap;
import java.util.Map;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author Vorontsov
 */
public class SimpleListComponent extends ListComponent{

    public SimpleListComponent(Object[] listValues, String id) {
        super(WrCollections.aggregate(listValues, new WrCollections.AggregateCondition<Object,Map<String,String>>(){

            @Override
            public Map<String, String> aggregateItem(Object item, Map<String, String> currentResult) {
                currentResult.put(item.toString(), item.toString());
                return currentResult;
            }
        }, new HashMap<String,String>()), id);
    }
    
}
