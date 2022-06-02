package com.teun.moviemanager.config;

import com.teun.moviemanager.Filters.JWTAuthenticationFilter;
import com.teun.moviemanager.Filters.JWTAuthorizationFilter;
import com.teun.moviemanager.Services.AuthenticationUserDetailService;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

//test
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource corsConfigurationSource = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedHeader("*");
                corsConfiguration.addAllowedOrigin("*");
                corsConfiguration.addAllowedMethod("*");
                return corsConfiguration;
            };
            c.configurationSource(corsConfigurationSource);
        });
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthConfigurationConstants.SIGN_UP_URL).permitAll()
                .antMatchers(AuthConfigurationConstants.SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST,"/ws/**").permitAll()
                .antMatchers(HttpMethod.GET, "/ws/**").permitAll()
                .antMatchers(HttpMethod.POST,"/send-update").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/**").permitAll()
                .antMatchers(HttpMethod.GET, "/movielist/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/user/all").hasAnyAuthority("MODERATOR","ADMIN")
                .antMatchers(HttpMethod.POST, "/movies/**").hasAnyAuthority("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movies/id/**").hasAnyAuthority("MODERATOR", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }



}
