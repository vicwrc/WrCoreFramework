/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.actions;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vorontsov
 */
public interface WebAction {

    Object perform(HttpServletRequest request, String action)throws Exception;
    
}
