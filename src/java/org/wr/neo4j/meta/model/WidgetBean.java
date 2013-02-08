package org.wr.neo4j.meta.model;


/**
 *
 * @author vicwrc
 */
public class WidgetBean extends BaseBean{

    private final String url;

    public WidgetBean( long id, String url) {
        super(id);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "WidgetBean{" + "url=" + url + '}';
    }
    
    
    
}
