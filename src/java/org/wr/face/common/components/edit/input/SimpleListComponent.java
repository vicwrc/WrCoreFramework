package org.wr.face.common.components.edit.input;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author Vorontsov
 */
public class SimpleListComponent extends ListComponent{

    public SimpleListComponent(Object[] listValues, String id) {
        super(WrCollections.aggregate(listValues, new WrCollections.AggregateCondition<Object,List<Map.Entry<String, String>>>(){

            
            @Override
            public List<Map.Entry<String, String>> aggregateItem(final Object item, List<Map.Entry<String, String>> currentResult) {
                currentResult.add(new Map.Entry<String, String>() {

                   @Override
                    public String getKey() {
                        return item.toString();
                    }

                    @Override
                    public String getValue() {
                        return item.toString();
                    }

                    @Override
                    public String setValue(String value) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                return currentResult;
            }
        }, new LinkedList<Map.Entry<String, String>>()), id);
        
    }
    
}
