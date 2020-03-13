package com.example.usersmicroservice.filters;

import com.example.usersmicroservice.services.AuthenticationService;
import com.example.usersmicroservice.services.JwtService;
import com.example.usersmicroservice.web.dtos.JwtDto;
import com.example.usersmicroservice.web.dtos.UserDto;
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

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication currentPrincipal = SecurityContextHolder.getContext().getAuthentication();

        if (currentPrincipal == null) {

            String header = request.getHeader(AUTHORIZATION_HEADER_KEY);

            if (hasText(header) && header.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
                String jwt = header.substring(AUTHORIZATION_HEADER_PREFIX.length());
                try {
                    UserDetails userDetails = authenticationService.validate(jwt);
                    setRequestSession(request, userDetails);
                } catch (RuntimeException ex) {
                    log.warn(ex.getLocalizedMessage());
                }
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
