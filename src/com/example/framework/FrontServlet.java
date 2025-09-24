package com.example.framework;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class FrontServlet extends HttpServlet {

    private RouteResolver resolver;

    @Override
    public void init() throws ServletException {
        resolver = new RouteResolver();
        try {
            resolver.scanPackage("com.example.demo");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String path = req.getPathInfo(); 
            Object result = resolver.resolve(path);
            ViewResolver.render(result, req, resp);
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("Erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
