/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.page.handlers;

import org.junit.Assert;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.wr.neo4j.meta.model.page.RedirectPageBean;

/**
 *
 * @author Vorontsov
 */
public class RedirectPageHandlerTest {
    
    @Test
    public void testCreateRedirectUrl(){
        RedirectPageHandler handler = new RedirectPageHandler();
        RedirectPageBean redirectPage = new RedirectPageBean(1,null);
        redirectPage.setPageToRedirect("'create.jsp?parentId=' + getId()");
        Node node = new Node(){

            @Override
            public long getId() {
                return 123;
            }

            @Override
            public void delete() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Relationship> getRelationships() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasRelationship() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Relationship> getRelationships(RelationshipType... rts) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Relationship> getRelationships(Direction drctn, RelationshipType... rts) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasRelationship(RelationshipType... rts) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasRelationship(Direction drctn, RelationshipType... rts) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Relationship> getRelationships(Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasRelationship(Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Relationship> getRelationships(RelationshipType rt, Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasRelationship(RelationshipType rt, Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Relationship getSingleRelationship(RelationshipType rt, Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Relationship createRelationshipTo(Node node, RelationshipType rt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Traverser traverse(Order order, StopEvaluator se, ReturnableEvaluator re, RelationshipType rt, Direction drctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Traverser traverse(Order order, StopEvaluator se, ReturnableEvaluator re, RelationshipType rt, Direction drctn, RelationshipType rt1, Direction drctn1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Traverser traverse(Order order, StopEvaluator se, ReturnableEvaluator re, Object... os) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public GraphDatabaseService getGraphDatabase() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean hasProperty(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object getProperty(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object getProperty(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setProperty(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object removeProperty(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<String> getPropertyKeys() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Iterable<Object> getPropertyValues() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        Assert.assertEquals("create.jsp?parentId=123", handler.createRedirectUrl(node, redirectPage));
    }
    
}
