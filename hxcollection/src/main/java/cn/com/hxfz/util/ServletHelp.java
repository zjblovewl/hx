package cn.com.hxfz.util;

import java.io.*;
import javax.servlet.http.*;

/**
 * The Class ServletHelp.
 */
public class ServletHelp
{

    /**
     * The Constructor.
     */
    public ServletHelp()
    {
    }

    /**
     * Out request for json.
     *
     * @param request the request
     * @param response the response
     * @param res the res
     * @throws IOException the IO exception
     */
    public static void outRequestForJson(HttpServletRequest request, HttpServletResponse response, String res)
        throws IOException
    {
    	response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(res);
    }
    
    /**
     * Get real path.
     *
     * @param request the request
     * @param virtualPath the virtual path
     * @return the real path
     */
    public static String getRealPath(HttpServletRequest request, String virtualPath)
    {
        return request.getSession().getServletContext().getRealPath(virtualPath);
    }
}
