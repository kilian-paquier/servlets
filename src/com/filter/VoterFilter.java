package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "VoterFilter")
public class VoterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type != null){
            if (type.equals("admin"))
                chain.doFilter(req, resp);
            else
            {
                RequestDispatcher rd =req.getRequestDispatcher("error403.jsp");
                rd.include(req, resp);
            }
        }
        else{
            RequestDispatcher rd =req.getRequestDispatcher("error403.jsp");
            rd.include(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
