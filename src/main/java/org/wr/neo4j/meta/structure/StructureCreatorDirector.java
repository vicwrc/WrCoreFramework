package org.wr.neo4j.meta.structure;

import org.neo4j.graphdb.Node;
import org.wr.neo4j.core.DoInNewTransacton;
import org.wr.neo4j.core.Neo4jTransaction;
import org.wr.neo4j.meta.structure.creators.*;

/**
 *
 * @author vicwrc
 */
public class StructureCreatorDirector implements DoInNewTransacton<Node>{

    @Override
    public Node perform(Neo4jTransaction tx) throws Exception {
        Node root = null;
        try{
            root = tx.getDbManager().getDbService().getNodeById(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(null != root) {
           return root;
        }else{
            return buildRoot(tx);
        }
    }

    private Node buildRoot(Neo4jTransaction tx) {
        Node root = new RootCreator().createNode(tx, null);
        new RootDataCreator().createNode(tx, root);
        Node rootMetadata = new RootMetadataCreator().createNode(tx, root);
        new WidgetsRootCreator().createNode(tx, rootMetadata);
        Node attrsRoot = new AttributesRootCreator().createNode(tx, rootMetadata);
        Node nameAttr = new NameAttributeCreator().createNode(tx, attrsRoot);
        new BaseObjectTypeCreator(nameAttr).createNode(tx, rootMetadata);
        
        return root;
    }
   
}
