/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.request;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vorontsov
 */
public class RequestParsersManager {

    private Map<String, Map<String,WebRequestParser>> page2Parsers;

    public Map<String, Map<String, WebRequestParser>> getPage2Parsers() {
        return page2Parsers;
    }

    public void setPage2Parsers(Map<String, Map<String, WebRequestParser>> page2Parsers) {
        this.page2Parsers = page2Parsers;
    }

    public Map<String, WebRequestParser> getParsersForCurrentPage(HttpServletRequest request){
        return page2Parsers.get(request.getRequestURI());
    }
   
    public Object parse(HttpServletRequest request, String action){
        Map<String, WebRequestParser> actionsForCurrentPage = getParsersForCurrentPage(request);
        if(null == actionsForCurrentPage){
            return null;
        }
        WebRequestParser parser = actionsForCurrentPage.get(action);
        if(null == parser){
            return null;
        }
        return parser.parseParams(request);
    }

}
