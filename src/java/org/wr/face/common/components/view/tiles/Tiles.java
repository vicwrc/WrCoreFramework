/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.tiles;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.NestedComponent;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public class Tiles extends NestedComponent{

    public Tiles(List<PageBean> pages, Node node){
        for(PageBean page : pages){
            if(!page.isHidden()){
                this.children.add(new Tile(page, node));
            }
        }
    }
    
    @Override
    protected String renderStartBlock() {
        return "";
    }

    @Override
    protected String renderEndBlock() {
        return "";
    }
       
       
}
