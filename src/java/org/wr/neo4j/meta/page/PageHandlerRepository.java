package org.wr.neo4j.meta.page;

import java.util.HashMap;

/**
 *
 * @author vorontsov
 */
public class PageHandlerRepository extends HashMap<String, PageHandler> {

    protected PageHandler create(String className) {
        try {
            return (PageHandler) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageHandler get(Object key) {
        if (null == key || "".equals(key.toString())) {
            return null;
        }
        PageHandler handler = super.get(key);
        if (null == handler) {
            handler = create((String) key);
            put((String) key, handler);
        }
        return handler;
    }
}
