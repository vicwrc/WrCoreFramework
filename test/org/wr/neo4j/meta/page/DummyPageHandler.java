/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.page;

import org.wr.neo4j.meta.page.PageHandler;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Vorontsov
 */
public class DummyPageHandler implements PageHandler{

    @Override
    public void process(ServletRequest request, ServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
