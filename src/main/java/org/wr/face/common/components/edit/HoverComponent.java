package org.wr.face.common.components.edit;

/**
 *
 * @author Vorontsov
 */
public class HoverComponent extends InputComponent{

    private String name;
    private String url;

    public HoverComponent(String name, String url, String id) {
        super(id);
        this.name = name;
        this.url = url;
    }

    public HoverComponent(String id) {
        super(id);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String renderHtml() {
        return "<a href=\""+url+"\">"+name+"</a>";
    }
    
}
