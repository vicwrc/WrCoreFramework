/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.actions;

import java.util.Map;
import org.wr.face.request.WebRequestParser;

/**
 *
 * @author vorontsov
 */
public abstract class AbstractWebAction implements WebAction{

    protected Map<String, WebRequestParser> pageRequestParsers;

    public void setPageRequestParsers(Map<String, WebRequestParser> pageRequestParsers) {
        this.pageRequestParsers = pageRequestParsers;
    }




}
