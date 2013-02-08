/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.cache.builders;

import java.util.List;
import org.neo4j.graphdb.Node;
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
           if(builder.isAppropriate(node)){
               return builder.build(tx, parent, node);
           }
       }
       return null;
    }

    @Override
    public boolean isAppropriate(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
