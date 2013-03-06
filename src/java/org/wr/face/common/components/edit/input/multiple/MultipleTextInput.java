package org.wr.face.common.components.edit.input.multiple;

import java.util.HashMap;
import java.util.Map;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public class MultipleTextInput extends MultipleInput{

    public MultipleTextInput(String id) {
        super(id);
    }
    
    public MultipleTextInput(Object[] listValues, String id) {
        super( id);
        setListValues(listValues);
    }
    
    public final void setListValues(Object[] listValues){
        WrCollections.aggregate(listValues, new WrCollections.AggregateCondition<Object,Map<String,String>>(){

            @Override
            public Map<String, String> aggregateItem(Object item, Map<String, String> currentResult) {
                currentResult.put(String.valueOf(item), String.valueOf(item));
                return currentResult;
            }
        }, new HashMap<String,String>());
    }
}
