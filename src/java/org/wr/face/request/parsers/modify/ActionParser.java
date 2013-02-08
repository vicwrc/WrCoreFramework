/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.request.parsers.modify;

import javax.servlet.http.HttpServletRequest;
import org.wr.face.request.WebRequestParser;

/**
 *
 * @author vorontsov
 */
public class ActionParser implements WebRequestParser<String>{

    public String parseParams(HttpServletRequest request) {
        return request.getParameter("action");
    }

}
