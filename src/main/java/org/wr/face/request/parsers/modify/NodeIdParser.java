/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.request.parsers.modify;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.wr.face.request.WebRequestParser;

/**
 *
 * @author vorontsov
 */
public class NodeIdParser implements WebRequestParser<Long> {

    public Long parseParams(HttpServletRequest request) {
        if(StringUtils.isEmpty(request.getParameter("nodeid"))){
            return null;
        }
        return new Long(request.getParameter("nodeid"));
    }

}
