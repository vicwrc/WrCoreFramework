/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.view.tiles;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.wr.face.common.components.NestedComponent;
import org.wr.neo4j.meta.model.PageBean;

/**
 *
 * @author vorontsov
 */
public class Tiles extends NestedComponent{

    private Map<String, TileGroup> tileGroups = new HashMap<>();
    
    public Tiles(List<PageBean> pages, Node node){
        for(PageBean page : pages){
            if(!page.isHidden()){
                TileGroup group = getTileGroup(page);
                group.addChild(new Tile(page, node));
            }
        }
        for(String groupName : groupNames()){
            addChild(tileGroups.get(groupName));
        }
    }
    
    private List<String> groupNames(){
        List<String> names = new LinkedList<>(tileGroups.keySet());
        Collections.sort(names, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return names;
    }
    
    private TileGroup getTileGroup(PageBean page){
        TileGroup group = tileGroups.get(page.getTileGroup());
        if(null == group){
            group = new TileGroup();
            tileGroups.put(page.getTileGroup(), group);
        }
        return group;
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
