package com.example.springboardapi.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns= "/*")
public class XSSFilter implements Filter {
    public FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init");
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);

    }

}
