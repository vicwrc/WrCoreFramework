/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view;

import org.wr.face.common.components.NestedComponent;

/**
 *
 * @author vorontsov
 */
public class SimpleTableComponent extends NestedComponent{

    @Override
    protected String renderStartBlock() {
        return "<table><tbody>";
    }

    @Override
    protected String renderEndBlock() {
        return "</tbody></table>";
    }
    
    
    
    
}
