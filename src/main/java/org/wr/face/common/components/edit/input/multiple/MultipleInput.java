/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit.input.multiple;

import java.util.HashMap;
import java.util.Map;
import org.wr.face.common.components.edit.InputComponent;
import org.wr.face.common.components.edit.input.SingleTextComponent;

/**
 *
 * @author vorontsov
 */
public class MultipleInput extends InputComponent {

    private SingleTextComponent dataInputField;
    private Map<String, String> listValues;
    
    public MultipleInput(String id) {
        this(new HashMap<String, String>(),id);
        dataInputField = new SingleTextComponent(id+"_fake");
    }

    public MultipleInput(Map<String, String> listValues, String id) {
        super(id);
        this.listValues = listValues;
    }

    public void setListValues(Map<String, String> listValues) {
        this.listValues = listValues;
    }
    
    public void setDataInputField(SingleTextComponent dataInputField) {
        this.dataInputField = dataInputField;
    }

    protected String renderOptions() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> keyValue : listValues.entrySet()) {
            sb.append("<option value=\"").append(keyValue.getKey()).append("\" ").append(">").append(keyValue.getValue()).append("</option>");
        }
        return sb.toString();
    }
    
    @Override
    public String renderHtml() {
        return "<div  class=\"input-control select\">\n" +
"        <select multiple=\"multiple\" id=\""+this.getId()+"\" >\n" +
renderOptions()+
"        </select>\n" +
"\n" +
"        <br>\n" +
          dataInputField.renderHtml() +
"        <input type=\"button\" value=\"add\" onclick=\"new MultipleInput('"+this.getId()+"').add();\">\n" +
"        <input type=\"button\" value=\"remove\" onclick=\"new MultipleInput('"+this.getId()+"').removeSelected();\">\n" +
"    </div>";
    }
    
}
