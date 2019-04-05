package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "VoteFilter")
public class VoteFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString();
        if(type != null){
            if (type.equals("candidat") || type.equals("votant"))
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
