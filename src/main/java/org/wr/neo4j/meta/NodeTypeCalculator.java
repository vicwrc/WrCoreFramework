/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;


/**
 *
 * @author vorontsov
 */
public class NodeTypeCalculator {

    public MetaType getType(Node node) {
        try {
            Relationship ref2ot = node.getSingleRelationship(BaseReationTypes.OBJECT_TYPE, Direction.OUTGOING);
            return null != ref2ot ? MetaType.REGULAR : getMetaType(node);
        } catch (Exception e) {
            return getMetaType(node);
        }
    }

    protected MetaType getMetaType(Node node) {
        return MetaType.valueOf((String) node.getProperty(MetaDataConstants.ALL_META_TYPE));
    }
}
