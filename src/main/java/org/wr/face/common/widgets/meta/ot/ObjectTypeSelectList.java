package org.wr.face.common.widgets.meta.ot;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.wr.face.common.components.edit.input.ListComponent;
import org.wr.neo4j.meta.cache.MetaCacheController;
import org.wr.neo4j.meta.model.ObjectTypeBean;
import org.wr.utils.collections.SimpleEntry;

/**
 *
 * @author Vorontsov
 */
public class ObjectTypeSelectList extends ListComponent {

    private final String defaultObjectId;
    private MetaCacheController metaCacheController;

    public ObjectTypeSelectList(String defaultObjectId, String id) {
        super(new LinkedList<Map.Entry<String, String>>(), id);
        this.defaultObjectId = defaultObjectId;
    }

    @Override
    public String renderHtml() {
        List<Map.Entry<String, String>> listValues = new LinkedList<>();
        gatherObjectTypeInformation(listValues, getStartObjectType(), 0);
        this.setListValues(listValues);

        return super.renderHtml();
    }

    protected MetaCacheController getMetaCacheController() {
        if (null == metaCacheController) {
            metaCacheController = this.getContext().getBean(MetaCacheController.class);
        }
        return metaCacheController;
    }

    protected void gatherObjectTypeInformation(List<Map.Entry<String, String>> listValues, ObjectTypeBean bean, int level) {
        listValues.add(new SimpleEntry(String.valueOf(bean.getId()), getFormattedName(bean.getName(), level)));
        for (ObjectTypeBean child : bean.getChildObjectTypes()) {
            gatherObjectTypeInformation(listValues, child, level + 1);
        }
    }

    protected String getFormattedName(String name, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < offset; i++) {
            sb.append("&nbsp;&nbsp;&nbsp;");
        }
        return sb.toString()+name;
    }

    protected ObjectTypeBean getStartObjectType() {
        if (StringUtils.isEmpty(defaultObjectId)) {
            return getMetaCacheController().getObjectTypeService().getById(
                    getMetaCacheController().getMetaCacheService().getBaseObjectType().getId());
        }
        return getMetaCacheController().getObjectTypeService().getById(Long.valueOf(defaultObjectId));
    }
}
