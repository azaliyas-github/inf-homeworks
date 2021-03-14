package ru.itis.restoke.filters;

import ru.itis.restoke.servlets.helpers.HeaderFtlHelper;
import ru.itis.restoke.servlets.helpers.HttpSessionHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getRequestURI().contains("/login") | !httpServletRequest.getRequestURI().contains("/sign-up")) {
            HttpSessionHelper.createSession(httpServletRequest);

            HeaderFtlHelper.toSetHidden(httpServletRequest);
        }
        chain.doFilter(request, response);

    }

}
