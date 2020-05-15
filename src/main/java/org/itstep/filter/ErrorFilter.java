package org.itstep.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebFilter("/*")
public class ErrorFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("IN FILTER");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestUrl = req.getRequestURI();
        request.setAttribute("request_url", requestUrl);
        try {
            chain.doFilter(request, response);
        } catch (Exception ex) {
            log.error("Process request failed" + requestUrl, ex);
            System.out.println("path: " + req.getContextPath());
            resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/error");
        }
    }


}
