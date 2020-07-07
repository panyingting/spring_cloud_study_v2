package com.common.server.istudy.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterDemo implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("\nfilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println(" do filter before");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println(" do filter after");
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }
}
