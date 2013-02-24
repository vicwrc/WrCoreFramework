/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.builders;

import org.wr.face.common.WebSpringAware;
import org.wr.face.servlet.ParamExtendServletRequestWrapper;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author Vorontsov
 */
public abstract class AbstractWebToEntityBuilder extends WebSpringAware {
    
    protected BaseBean getParent(ParamExtendServletRequestWrapper wrapper){
        Long id = wrapper.getParameter("parentId", Long.class);
        if(null == id){
            return null;
        }
        return new BaseBean(id);
    }
}
