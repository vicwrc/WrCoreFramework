/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.request.parsers.modify;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.wr.face.request.WebRequestParser;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;


/**
 *
 * @author vorontsov
 */
public class NodeParameterParser implements WebRequestParser<Map<String,Object>>{

    public Map<String, Object> parseParams(HttpServletRequest request) {
        return splitParams(parseList(request));
    }

    protected Map<String,Object> splitParams(List<Pair> pairs){
        Map<String,List> paramsProto = new HashMap<String,List>();

        for(Pair pair : pairs){
            List values = paramsProto.get(pair.key);
            if(null == values){
                values = new LinkedList();
                paramsProto.put(pair.key, values);
            }
            values.add(pair.value);
        }

        return convertMapToNeo4j(paramsProto);
    }

    protected Map<String,Object> convertMapToNeo4j(Map<String,List> paramsProto){
        Map<String,Object> params = new HashMap<String,Object>();
        for(Entry<String,List> paramEntry : paramsProto.entrySet()){
            if(paramEntry.getValue().size() == 1){
                params.put(paramEntry.getKey(), paramEntry.getValue().iterator().next());
            }
            if(paramEntry.getValue().size() > 1){
                params.put(paramEntry.getKey(), paramEntry.getValue().toArray(new Object[paramEntry.getValue().size()]));
            }
        }
        return params;
    }


    protected List<Pair> parseList(HttpServletRequest request){
        List<Pair> pairList = new LinkedList<Pair>();

        int i = 0;
        boolean hasNext = true;
        while(hasNext){
            String key = request.getParameter("keyname"+i);
            if(StringUtils.isEmpty(key)){
                hasNext = false;
            }else{
                String value = request.getParameter("keyvalue"+i);
                pairList.add(new Pair(key,value));
            }
            i++;
        }

        return pairList;
    }

    public class Pair{
        private String key;
        private Object value;

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

    }

}
