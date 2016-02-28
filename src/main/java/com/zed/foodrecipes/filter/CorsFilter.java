package com.zed.foodrecipes.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Arnaud on 01/05/2015.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);
    @Value("${application.url}")
    private String applicationUrl;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", applicationUrl);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!request.getMethod().equals("OPTIONS")) {
            try {
                chain.doFilter(req, res);
            } catch (IOException e) {
                logger.error("{doFilter} IOException ", e);
            } catch (ServletException e) {
                logger.error("{doFilter} ServletException ", e);
            }
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
