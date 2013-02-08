package org.wr.neo4j.meta.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.wr.utils.collections.WrCollections;
import org.wr.utils.collections.WrCollections.FilterCondition;

/**
 *
 * @author vicwrc
 */
public class ObjectTypeBean extends BaseBean {

    protected List<AttributeBean> parentAttributes;
    protected List<AttributeBean> currentAttributes;
    protected List<PageBean> parentPages;
    protected List<PageBean> currentPages;

    public ObjectTypeBean(long id) {
        super(id);
    }

    /* Attributes and Wigets*/
    public List<AttributeBean> getParentAttributes() {
        return Collections.unmodifiableList(parentAttributes);
    }

    public List<AttributeBean> getCurrentAttributes() {
        return currentAttributes;
    }

    public void rebuildAttributes() {
        if (getParent() instanceof ObjectTypeBean) {
            parentAttributes = ((ObjectTypeBean) getParent()).getAllAttributes();
        } else {
            parentAttributes = Collections.EMPTY_LIST;
        }
    }
    
    public void rebuildAttributesRecursive(){
        rebuildAttributes();
        for( ObjectTypeBean child : getChildObjectTypes()){
            child.rebuildAttributesRecursive();
        }
    }

    public List<AttributeBean> getAllAttributes() {
        List<AttributeBean> result = new LinkedList<>(currentAttributes);
        result.addAll(parentAttributes);
        return Collections.unmodifiableList(result);
    }

    public List<PageBean> getParentPages() {
        return Collections.unmodifiableList(parentPages);
    }

    public List<PageBean> getCurrentPages() {
        return new LinkedList(WrCollections.<BaseBean>filter(getChildren(), new FilterCondition<BaseBean>() {
            @Override
            public boolean isAppropriate(BaseBean element) {
                return (element instanceof PageBean);
            }
        }));
    }

    public List<PageBean> getAllPages() {
        List<PageBean> result = new LinkedList<>(currentPages);
        result.addAll(WrCollections.<PageBean>filter(parentPages, new FilterCondition<PageBean>() {
            @Override
            public boolean isAppropriate(PageBean element) {
                return element.isExtendable();
            }
        }));
        return Collections.unmodifiableList(result);
    }

    public void setParentAttributes(List<AttributeBean> parentAttributes) {
        this.parentAttributes = parentAttributes;
    }

    public void setCurrentAttributes(List<AttributeBean> currentAttributes) {
        this.currentAttributes = currentAttributes;
    }

    public void setParentPages(List<PageBean> parentPages) {
        this.parentPages = parentPages;
    }

    public void setCurrentPages(List<PageBean> currentPages) {
        this.currentPages = currentPages;
    }

    public List<ObjectTypeBean> getChildObjectTypes() {
        return new LinkedList(WrCollections.<BaseBean>filter(getChildren(), new FilterCondition<BaseBean>() {
            @Override
            public boolean isAppropriate(BaseBean element) {
                return (element instanceof ObjectTypeBean);
            }
        }));
    }
}
