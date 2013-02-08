/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wr.face;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author vorontsov
 */
public class ErrorHelper {

    public static String getHtmlError(Throwable e) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("<br><font color=red>").append(e.getMessage()).append("<br>");
        for(StackTraceElement ste :e.getStackTrace()){
            sb.append(ste.toString()).append("<br>");
        }
        sb.append("</font>");

        if(null != e.getCause()){
            sb.append("cause<br>");
            sb.append(getHtmlError(e.getCause()));
        }
        return sb.toString();
    }

    public static void printError(Throwable e, JspWriter out) throws IOException{
        out.print("<br><font color=red>"+e.getMessage()+"<br>");
        for(StackTraceElement ste :e.getStackTrace()){
            out.print(ste.toString()+"<br>");
        }
        out.print("</font>");

        if(null != e.getCause()){
            out.print("cause<br>");
            printError(e.getCause(),out);
        }
    }
}
