/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.widgets.meta.ot;

import org.wr.face.common.components.edit.HoverComponent;
import org.wr.neo4j.meta.cache.MetaCacheController;
import org.wr.neo4j.meta.model.ObjectTypeBean;

/**
 *
 * @author Vorontsov
 */
public class ViewObjectTypeById extends HoverComponent{

    private MetaCacheController metaCacheController;
    
    public ViewObjectTypeById(String id) {
        super(id);
    }
    
    protected MetaCacheController getMetaCacheController() {
        if (null == metaCacheController) {
            metaCacheController = this.getContext().getBean(MetaCacheController.class);
        }
        return metaCacheController;
    }
    
    @Override
    public String renderHtml() {
        ObjectTypeBean bean = getMetaCacheController().getObjectTypeService().getById(Long.valueOf(this.getId()));
        this.setName(bean.getName());
        this.setUrl("index.jsp?id="+bean.getId());
        return super.renderHtml();
    }
    
}
