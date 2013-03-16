/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face.request;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vorontsov
 */
public interface WebRequestParser<T> {


    T parseParams(HttpServletRequest request);

}
