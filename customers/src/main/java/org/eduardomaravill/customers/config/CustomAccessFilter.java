package org.eduardomaravill.customers.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.eduardomaravill.customers.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@NoArgsConstructor
@Component
public class CustomAccessFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean authorized = isAuthorized(request);
        if (authorized) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList()));
            filterChain.doFilter(request, response);
        }else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }


    private boolean isAuthorized(HttpServletRequest request) {
        String currentUrl = request.getRequestURI();
        String[] availableURLs = {"/api/auth/login","/api/auth/register"};
        boolean authorize = Arrays.asList(availableURLs).contains(currentUrl);
        boolean isApiResource = currentUrl.startsWith("/api");
        if (authorize || !isApiResource){
            return true;
        }
        try {
            String authorizationToken = request.getHeader("Authorization");
            String userId = JwtUtil.getUserIdByToken(authorizationToken);
          return true;
        }catch (Exception _){
            return false;
        }
    }

}
