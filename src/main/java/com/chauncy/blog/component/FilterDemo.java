package com.chauncy.blog.component;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class FilterDemo implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[FilterDemo init……]");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("[doFilter before……]");
        filterChain.doFilter(request, response);
        log.info("[doFilter after……]");
    }

    @Override
    public void destroy() {
        log.info("[FilterDemo destroy……]");
    }
}
