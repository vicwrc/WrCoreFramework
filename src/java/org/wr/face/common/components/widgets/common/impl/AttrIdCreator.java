/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.widgets.common.impl;

import org.wr.neo4j.meta.model.AttributeBean;

/**
 *
 * @author vorontsov
 */
public class AttrIdCreator {
       
    public static String createAttrId(AttributeBean attr){
        return "attr_"+attr.getId();
    }
}
