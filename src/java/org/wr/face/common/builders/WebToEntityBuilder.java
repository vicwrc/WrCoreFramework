package org.wr.face.common.builders;

import javax.servlet.http.HttpServletRequest;
import org.wr.neo4j.meta.model.BaseBean;

/**
 *
 * @author vorontsov
 */
public interface WebToEntityBuilder<T extends BaseBean> {
    
    T build(HttpServletRequest request);
    
}
