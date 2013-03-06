/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.common;

import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public interface ComponentToAttrApplicability {
    
    boolean applicable(AttributeBean attr);
    
}
