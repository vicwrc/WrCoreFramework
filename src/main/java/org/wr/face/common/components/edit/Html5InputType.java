/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wr.face.common.components.edit;

/**
 *
 * @author Vorontsov
 */
public enum Html5InputType {

    TEXT("text"),
    COLOR("color"),
    DATE("date"),
    DATETIME("datetime"),
    DATETIME_LOCAL("datetime-local"),
    EMAIL("email"),
    MONTH("month"),
    NUMBER("number"),
    RANGE("range"),
    SEARCH("search"),
    TEL("tel"),
    TIME("time"),
    URL("url"),
    WEEK("week");
    
    
    private final String type;

    private Html5InputType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
