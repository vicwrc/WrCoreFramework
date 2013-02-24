/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.neo4j.meta.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vorontsov
 */
public class BaseBean {

    protected long id;
    protected String name;
    protected long order = 0;
    protected BaseBean parent;
    protected List<BaseBean> children = new LinkedList<>();

    public BaseBean(long id) {
        this.id = id;
    }

    public BaseBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BaseBean getParent() {
        return parent;
    }

    public void setParent(BaseBean parent) {
        this.parent = parent;
    }

    public List<BaseBean> getChildren() {
        return children;
    }

    public void setChildren(List<BaseBean> children) {
        this.children = children;
    }

    public BaseBean getChildById(long id) {
        for (BaseBean child : children) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

    public void remove() {
        if (null != getParent()) {
            getParent().getChildren().remove(this);
        }
    }

    @Override
    public String toString() {
        return "BaseBean{" + "id=" + id + ", name=" + name + ", order=" + order + '}';
    }
}
