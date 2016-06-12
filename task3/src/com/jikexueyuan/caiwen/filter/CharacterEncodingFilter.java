package com.jikexueyuan.caiwen.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by caiwen on 2016/6/10.
 */
public class CharacterEncodingFilter  implements Filter {
    private String characterEncoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        characterEncoding = "utf-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(characterEncoding);
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.setCharacterEncoding(characterEncoding);

    }

    @Override
    public void destroy() {
        characterEncoding = null;
    }
}
