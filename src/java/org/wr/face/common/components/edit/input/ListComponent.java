/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit.input;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.wr.face.common.components.edit.InputComponent;


/**
 *
 * @author Vorontsov
 */
public class ListComponent extends InputComponent {

    private List<Map.Entry<String, String>> listValues;
    private String deafult = null;

    public ListComponent(List<Map.Entry<String, String>> listValues, String id) {
        super(id);
        this.listValues = listValues;
        
    }

    public List<Map.Entry<String, String>> getListValues() {
        return listValues;
    }

    public void setListValues(List<Entry<String, String>> listValues) {
        this.listValues = listValues;
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
        for (Map.Entry<String, String> keyValue : listValues) {
            StringBuilder append = sb.append("<option value=\"").append(keyValue.getKey()).append("\" ").append(keyValue.getKey().equals(deafult)?" selected":"").append(">").append(keyValue.getValue()).append("</option>");
        }
        return sb.toString();
    }

    @Override
    public String renderHtml() {
        return "<div class=\"input-control select\" > "
                + "<select name=\"" + getId() + "\" "+putAttributes()+"> "
                + renderOptions()
                + "</select>"
                + "</div>";
    }
    
    protected String putAttributes(){
        return "";
    }
}
