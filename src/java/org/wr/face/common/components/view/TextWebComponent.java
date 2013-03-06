package org.wr.face.common.components.view;

import org.wr.face.common.components.WebComponent;

/**
 *
 * @author vicwrc
 */
@Deprecated
public class TextWebComponent extends WebComponent {

    private final String name;
    private final String value;

    public TextWebComponent(String name, String value) {
        this.name = name;
        this.value = value;
        this.setSingleRenderable(false);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String renderHtml() {
        return "<tr><td width=\"25%\">" + name + "</td><td>" + value + "</td></tr>";
    }
}
