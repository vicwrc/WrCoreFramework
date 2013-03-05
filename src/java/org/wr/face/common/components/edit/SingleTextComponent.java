package org.wr.face.common.components.edit;

/**
 *
 * @author Vorontsov
 */
public class SingleTextComponent extends InputComponent {

    private String hint="";
    private String defaultValue="";
    private Html5InputType type = Html5InputType.TEXT;

    public SingleTextComponent(String id) {
        super(id);
        this.singleRenderable = false;
    }

    public Html5InputType getType() {
        return type;
    }

    public SingleTextComponent setType(Html5InputType type) {
        this.type = type;
        return this;
    }
    
    public String getHint() {
        return hint;
    }

    public SingleTextComponent setHint(String hint) {
        this.hint = hint;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public SingleTextComponent setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
    
    @Override
    public String renderHtml() {
        return "     <div class=\"input-control text\"> "
                + "<input type=\"text\" name=\"" + getId() + "\" value=\""+getDefaultValue()+"\"  placeholder=\""+hint+"\" "+getExtraAttributes()+" />  "
               + "</div>";
    }

    protected String getExtraAttributes() {
        return "";
    }
    
    
}
