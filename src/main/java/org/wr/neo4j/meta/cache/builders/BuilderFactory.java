/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders;

import java.util.List;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author Vorontsov
 */
public class BuilderFactory implements CommonBuilder<BaseBean>{
    
    private List<CommonBuilder<BaseBean>> builders;

    public void setBuilders(List<CommonBuilder<BaseBean>> builders) {
        this.builders = builders;
    }

    @Override
    public BaseBean build(Neo4jTransaction tx, BaseBean parent, Node node) {
       for(CommonBuilder<BaseBean> builder : builders){
           if(isAppropriate(node,builder)){
               return builder.build(tx, parent, node);
           }
       }
       return null;
    }

    public boolean isAppropriate(Node node, CommonBuilder<BaseBean> builder) {
        try {
            return builder.isAppropriate(node);
        }catch (NotFoundException e) {
            return false;
        }
    }
    
    @Override
    public boolean isAppropriate(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
