/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit;

import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vorontsov
 */
public class ListComponent extends InputComponent {

    private final Map<String, String> listValues;
    private String deafult = null;

    public ListComponent(Map<String, String> listValues, String id) {
        super(id);
        this.listValues = listValues;
        
    }

    public Map<String, String> getListValues() {
        return listValues;
    }

    public String getDeafult() {
        return deafult;
    }

    public ListComponent setDeafult(String deafult) {
        this.deafult = deafult;
        return this;
    }

    protected String renderOptions() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> keyValue : listValues.entrySet()) {
            StringBuilder append = sb.append("<option value=\"").append(keyValue.getKey()).append("\" ").append(keyValue.getKey().equals(deafult)?" selected":"").append(">").append(keyValue.getValue()).append("</option>");
        }
        return sb.toString();
    }

    @Override
    public String renderHtml() {
        return "<div class=\"input-control select\"> "
                + "<select name=\"" + getId() + "\"> "
                + renderOptions()
                + "</select>"
                + "</div>";
    }
}
