package org.wr.face.common;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.wr.face.SpringContextParser;

/**
 *
 * @author vorontsov
 */
public abstract class WebSpringAware {
    
    public WebApplicationContext getContext(HttpServletRequest request){
        return SpringContextParser.getSpringContext(request);
    }
    
}
