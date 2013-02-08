/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta;

import org.neo4j.graphdb.RelationshipType;

/**
 *
 * @author vicwrc
 */
public enum BaseReationTypes  implements RelationshipType{
    
    /* All objects relations*/
    PARENT,
    
    /* Base object relations */
    OBJECT_TYPE,
    REFERENCE,
    
    /* MetaData relations*/
    ATTRIBUTES,
    WIDGETS,
    USERS
    
}
