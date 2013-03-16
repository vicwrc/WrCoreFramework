/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vorontsov
 */
public class IdParser {

    public static long parseLong(String idStr){
        try{
            if(!StringUtils.isEmpty(idStr)){
                return Long.parseLong(idStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public static long parseId(HttpServletRequest request){
        return parseLong(request.getParameter("id"));
    }
    
    public static long parseParentId(HttpServletRequest request){
        return parseLong(request.getParameter("parentId"));
    }
    
    
}
