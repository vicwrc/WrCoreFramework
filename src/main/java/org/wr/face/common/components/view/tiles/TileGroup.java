package org.wr.face.common.components.view.tiles;

import org.wr.face.common.components.NestedComponent;

/**
 *
 * @author vorontsov
 */
public class TileGroup extends NestedComponent{

    @Override
    protected String renderStartBlock() {
        return "<div class=\"tile-group tile-drag\">";
    }

    @Override
    protected String renderEndBlock() {
        return "</div>";
    }
    
}
