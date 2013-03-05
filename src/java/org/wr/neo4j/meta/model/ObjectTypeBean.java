package org.wr.neo4j.meta.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.wr.utils.collections.WrCollections;
import org.wr.utils.collections.WrCollections.FilterCondition;

/**
 *
 * @author vicwrc
 */
public class ObjectTypeBean extends BaseBean {

    protected List<AttributeBean> allAttributes = null;
    protected List<AttributeBean> currentAttributes = new LinkedList<>();
    protected List<PageBean> allPages = null;
    protected List<PageBean> currentPages = new LinkedList<>();

    public ObjectTypeBean(long id) {
        super(id);
    }

    /* Attributes and Wigets*/
    public List<AttributeBean> getCurrentAttributes() {
        return currentAttributes;
    }

    public void rebuildAttributes() {
        if (getParent() instanceof ObjectTypeBean) {
            allAttributes = new LinkedList<>(((ObjectTypeBean) getParent()).getAllAttributes());
            allAttributes.addAll(currentAttributes);
        } else {
            allAttributes = currentAttributes;
        }
        Collections.sort(allAttributes,
                new Comparator<AttributeBean>() {
                    @Override
                    public int compare(AttributeBean o1, AttributeBean o2) {
                        return (int) (o1.getOrder() - o2.getOrder());
                    }
                });
    }

    public void rebuildAttributesRecursive() {
        rebuildAttributes();
        for (ObjectTypeBean child : getChildObjectTypes()) {
            child.rebuildAttributesRecursive();
        }
    }

    public List<AttributeBean> getAllAttributes() {
        if (null == allAttributes) {
            rebuildAttributes();
        }
        return Collections.unmodifiableList(allAttributes);
    }

    public List<PageBean> getCurrentPages() {
        return new LinkedList(WrCollections.<BaseBean>filter(getChildren(), new FilterCondition<BaseBean>() {
            @Override
            public boolean isAppropriate(BaseBean element) {
                return (element instanceof PageBean);
            }
        }));
    }

    public void rebuildPages() {
        allPages = new LinkedList<>(currentPages);
        List<PageBean> parentPages = (getParent() instanceof ObjectTypeBean)
                ? ((ObjectTypeBean) getParent()).getAllPages() : Collections.EMPTY_LIST;
        allPages.addAll(WrCollections.<PageBean>filter(parentPages, new FilterCondition<PageBean>() {
            @Override
            public boolean isAppropriate(PageBean element) {
                return element.isExtendable();
            }
        }));
    }

    public void rebuildPagesRecursive() {
        rebuildPages();
        for (ObjectTypeBean child : getChildObjectTypes()) {
            child.rebuildPagesRecursive();
        }
    }

    public List<PageBean> getAllPages() {
        if (null == allPages) {
            rebuildPages();
        }
        return Collections.unmodifiableList(allPages);
    }

    public void setCurrentAttributes(List<AttributeBean> currentAttributes) {
        this.currentAttributes = currentAttributes;
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

    @Override
    public void remove() {
        super.remove();
        removeAttributeAssociationRecursively();
    }

    protected void removeAttributeAssociationRecursively() {
        for (AttributeBean attr : getAllAttributes()) {
            attr.getObjectTypes().remove(this);
        }
        for (ObjectTypeBean ot : this.getChildObjectTypes()) {
            ot.removeAttributeAssociationRecursively();
        }
    }
}
