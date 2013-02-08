package org.wr.neo4j.meta.cache.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.wr.neo4j.core.Neo4jDBManager;
import org.wr.neo4j.core.operations.DeleteNodeOperation;
import org.wr.neo4j.meta.model.BaseBean;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.neo4j.meta.cache.services.MetaCacheService;
import org.wr.neo4j.meta.cache.services.ObjectTypeService;

/**
 *
 * @author vicwrc
 */
public class ObjectTypeServiceImpl implements ObjectTypeService{

    private Map<Long,ObjectTypeBean> objectTypes = new HashMap<>();
    private Neo4jDBManager manager;
    private DeleteNodeOperation deleteNodeOperation;
    private MetaCacheService metaCacheService;

    public void setMetaCacheService(MetaCacheService metaCacheService) {
        this.metaCacheService = metaCacheService;
    }     

    public void setManager(Neo4jDBManager manager) {
        this.manager = manager;
    }

    public void setDeleteNodeOperation(DeleteNodeOperation deleteNodeOperation) {
        this.deleteNodeOperation = deleteNodeOperation;
    }

    
    public void init() throws Exception{
        BaseBean root = metaCacheService.getRootBean();
        BaseBean metaRoot = root.getChildById(metaCacheService.getMetaDataRoot().getId());
        ObjectTypeBean rootOT = (ObjectTypeBean)metaRoot.getChildById(metaCacheService.getBaseObjectType().getId());
        loadOtRecursively(rootOT);
    }
    
    protected void loadOtRecursively(ObjectTypeBean ot){
        objectTypes.put(ot.getId(), ot);
        for(ObjectTypeBean child : ot.getChildObjectTypes()){
           loadOtRecursively(child); 
        }
    }
    
    @Override
    public List<ObjectTypeBean> getAll() {
        return Collections.unmodifiableList(new LinkedList(objectTypes.values()));
    }

    @Override
    public ObjectTypeBean getById(long id) {
        return objectTypes.get(id);
    }

    @Override
    public void remove(long id) {
        ObjectTypeBean bean = getById(id);
        deleteNodeOperation.delete(manager.getDbService().getNodeById(id));
        objectTypes.remove(id);
    }

    @Override
    public ObjectTypeBean persist(ObjectTypeBean objectType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
