package org.wr.neo4j.meta.page;

import org.wr.neo4j.meta.page.PageHandlerRepository;
import org.wr.neo4j.meta.page.PageHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Vorontsov
 */
public class PageHandlerRepositoryTest {
    
    @Test
    public void testCreate(){
        PageHandlerRepository repository = new PageHandlerRepository();
        PageHandler handler = repository.create("org.wr.neo4j.meta.cache.page.DummyPageHandler");
        Assert.assertNotNull(handler);
    }    
    
}
