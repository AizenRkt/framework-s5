package com.example.framework;

import javax.servlet.http.*;

public class ViewResolver {

    public static void render(Object result, HttpServletRequest req, HttpServletResponse resp)
            throws Exception {

        if (result instanceof String) {
            String view = (String) result;
            req.getRequestDispatcher("/WEB-INF/views/" + view).forward(req, resp);
        } else {
            resp.setContentType("text/plain");
            resp.getWriter().write(result.toString());
        }
    }
}
