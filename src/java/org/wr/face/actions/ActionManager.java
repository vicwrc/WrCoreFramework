/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.actions;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.wr.face.request.RequestParsersManager;
import org.wr.face.request.WebRequestParser;

/**
 *
 * @author vorontsov
 */
public class ActionManager {

    private Map<String, Map<String,WebAction>> page2actions;
    public String actionName = "action";
    private RequestParsersManager requestParsersManager;

    public Map<String, Map<String, WebAction>> getPage2actions() {
        return page2actions;
    }

    public void setRequestParsersManager(RequestParsersManager requestParsersManager) {
        this.requestParsersManager = requestParsersManager;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setPage2actions(Map<String, Map<String, WebAction>> page2actions) {
        this.page2actions = page2actions;
    }

    public Object performAction(HttpServletRequest request) throws Exception{
        if(null == page2actions)return null;
        
        Map<String, WebAction> currentPageActions = page2actions.get(request.getRequestURI());
        if(null == currentPageActions)return null;
        
        Map<String, WebRequestParser> parsers = requestParsersManager.getParsersForCurrentPage(request);
        if(null == parsers)return null;
        
        WebRequestParser actionNameParser = parsers.get(actionName);
        if(null == actionNameParser)return null;
        
        WebAction action = currentPageActions.get(actionNameParser.parseParams(request).toString());
        if(null == action)return null;
        if(action instanceof AbstractWebAction){
            ((AbstractWebAction)action).setPageRequestParsers(parsers);
        }
        return action.perform(request, parsers.get(actionName).parseParams(request).toString());
    }

}
