/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components;

import java.util.LinkedList;
import java.util.List;
import org.wr.face.common.components.conditions.ComponentJoinCondition;
import org.wr.utils.collections.WrCollections;

/**
 *
 * @author vorontsov
 */
public abstract class NestedComponent extends WebComponent{

    protected List<WebComponent> children = new LinkedList<>();
    
    protected abstract String renderStartBlock();
    
    protected abstract String renderEndBlock();
    
    public NestedComponent addChild(WebComponent component){
        children.add(component);
        return this;
    }
    
    private String renderAllChildren(){
        return WrCollections.aggregate(children, ComponentJoinCondition.getInstance(), new StringBuilder()).toString();
    }
    
    @Override
    public String renderHtml() {
        return renderStartBlock() + renderAllChildren() + renderEndBlock();
    }
    
}
