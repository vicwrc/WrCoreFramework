package org.wr.face.common.components.edit.input.multiple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.wr.utils.WrArrays;
import org.wr.utils.WrMath;
import org.wr.utils.collections.WrCollections;
import scala.actors.threadpool.Arrays;

/**
 *
 * @author vorontsov
 */
public class MultipleTextInput extends MultipleInput {

    public MultipleTextInput(String id) {
        super(id);
    }

    public MultipleTextInput(Object[] listValues, String id) {
        super(id);
        setListValuesAsObject(listValues);
    }

    public void setListValuesAsObject(Object listValues) {
        this.setListValues(parseValues(listValues));
    }

    public final void setListValues(Object[] listValues) {
        setListValues(WrCollections.aggregate(listValues, new WrCollections.AggregateCondition<Object, Map<String, String>>() {
            @Override
            public Map<String, String> aggregateItem(Object item, Map<String, String> currentResult) {
                currentResult.put(String.valueOf(item), String.valueOf(item));
                return currentResult;
            }
        }, new HashMap<String, String>()));
    }

    public static String[] parseValues(Object value) {
        if (null == value) {
            return WrArrays.EMPTY_STRING_ARRAY;
        }
        if (value instanceof String[]) {
            return (String[]) value;
        }
        if (value instanceof Object[]) {
            return WrArrays.toStringArray((Object[]) value);
        }
        if (value instanceof int[]) {
            return WrArrays.toStringArray((int[]) value);
        }
        if (value instanceof String) {
            return WrArrays.singletonStringArray(value);
        }
        throw new UnsupportedOperationException();
    }
    
    public static String[] mergeValues(String[] originalValues, String[] added, String[] removed ) {
        List<String> values = new LinkedList<>();
        
        List<String> removedValues = new LinkedList<>(Arrays.asList(WrMath.nvl(removed,WrArrays.EMPTY_STRING_ARRAY)));
       

        for (String value : originalValues) {
            if (StringUtils.isEmpty(String.valueOf(value))){
                continue;
            }
            if (!isRemoved(value, removedValues)) {
                values.add(String.valueOf(value));
            }
        }
        for (String addValue : WrMath.nvl(added,WrArrays.EMPTY_STRING_ARRAY)) {
            if (StringUtils.isEmpty(addValue)) {
                continue;
            }
            values.add(addValue);
        }

        return values.toArray(new String[values.size()]);
    }
    
    private static boolean isRemoved(Object value, List<String> removedValues) {
        Iterator<String> i = removedValues.iterator();
        for (;i.hasNext();) {
            String delValue = i.next();
            if (String.valueOf(value).equals(delValue)) {
                i.remove();
                return true;
            }
        }
        return false;
    }
    
    
}
