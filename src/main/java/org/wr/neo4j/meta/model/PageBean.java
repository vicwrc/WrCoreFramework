package org.wr.neo4j.meta.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vorontsov
 */
public class PageBean extends BaseBean {

    private final String action;
    private boolean extendable = true;
    private boolean hidden = false;
    private String pathToImage;
    private String tileGroup;
    private String processClass;
    private String color;
    private String extraParams;
    private List<WidgetBean> widgets = new LinkedList<>();

    public PageBean(long id, String action) {
        super(id);
        this.action = action;
    }

    // created for Spring
    public PageBean(
            long id, 
            String action, 
            String name, 
            String processClass, 
            String color, 
            String extraParams, 
            boolean hidden, 
            List<WidgetBean> widgets,
            String pathToImage,
            String tileGroup
            ) {
        super(id);
        this.name = name;
        this.action = action;
        this.processClass = processClass;
        this.color = color;
        this.extraParams = extraParams;
        this.hidden = hidden;
        this.widgets = widgets;
        this.pathToImage = pathToImage;
        this.tileGroup = tileGroup;
    }
    
    public PageBean(
            long id, 
            String action, 
            String name, 
            String processClass, 
            String color, 
            String extraParams, 
            boolean hidden, 
            List<WidgetBean> widgets) {
        this(id, action, name, processClass, color, extraParams, hidden, widgets, null, null);
    }
    
    

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getProcessClass() {
        return processClass;
    }

    public void setProcessClass(String processClass) {
        this.processClass = processClass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(String extraParams) {
        this.extraParams = extraParams;
    }

    public String getAction() {
        return action;
    }

    public List<WidgetBean> getWidgets() {
        return widgets;
    }

    public Map<String, String> getExtraParametersForSet() {
        Map<String, String> keyValue = new HashMap<>();
        for (String entry : extraParams.split("&")) {
            String[] pair = entry.split("=");
            keyValue.put(pair[0], pair[1]);
        }

        return keyValue;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getTileGroup() {
        return tileGroup;
    }

    public void setTileGroup(String tileGroup) {
        this.tileGroup = tileGroup;
    }
    
    
}
