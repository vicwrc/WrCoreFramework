/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author vicwrc
 */
public class SpringContextParser{

    public static WebApplicationContext getSpringContext(HttpServletRequest request) {
        return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()) ;
    }
    
}
