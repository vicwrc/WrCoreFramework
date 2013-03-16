/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.page;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author vorontsov
 */
public interface PageHandler {
    
    void process(ServletRequest request, ServletResponse response);
    
}
