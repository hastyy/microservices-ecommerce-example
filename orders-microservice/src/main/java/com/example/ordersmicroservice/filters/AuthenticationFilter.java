package com.example.ordersmicroservice.filters;

import com.example.ordersmicroservice.services.user.JwtDto;
import com.example.ordersmicroservice.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication currentPrincipal = SecurityContextHolder.getContext().getAuthentication();

        if (currentPrincipal == null) {
            String header = request.getHeader(AUTHORIZATION_HEADER_KEY);
            if (hasText(header) && header.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
                String jwt = header.substring(AUTHORIZATION_HEADER_PREFIX.length());
                UserDetails userDetails = userService.validateJwt(new JwtDto(jwt));
                setRequestSession(request, userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setRequestSession(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
