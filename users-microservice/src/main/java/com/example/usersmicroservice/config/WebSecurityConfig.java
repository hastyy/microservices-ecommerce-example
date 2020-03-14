package com.example.usersmicroservice.config;

import com.example.usersmicroservice.services.UserService;
import com.example.usersmicroservice.web.controllers.AuthenticationController;
import com.example.usersmicroservice.web.controllers.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity  // Already includes @Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // JWT is not vulnerable to CSRF
        http.csrf().disable()
                // when no credentials are provided
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
                // don't create sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // allow login, registration and authentication requests
                .authorizeRequests()
                    .antMatchers(UserController.BASE_URL + "/**").permitAll()
                    .antMatchers(AuthenticationController.BASE_URL + "/**").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                    // protect any other resources
                    .anyRequest().authenticated();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * We need to override this method in order to add the @Bean annotation because Spring doesn't create an
     * AuthenticationManager bean by default anymore.
     * Without this we can't wire AuthenticationManager in other beans.
     * @return AuthenticationManager bean
     * @throws Exception on unsuccessful bean creation
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * This is invoked when the user tries to access a protected resource without providing any credentials.
     * We should just send a 401 Unauthorized response because there is no 'login page' to redirect to.
     */
    private AuthenticationEntryPoint unauthorizedHandler() {
        return (request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
