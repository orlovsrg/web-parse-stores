package org.itstep.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
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
        } catch (Throwable th) {
            log.error("Process request failed" + requestUrl, th);
            resp.sendRedirect("/error");
        }
    }


}
