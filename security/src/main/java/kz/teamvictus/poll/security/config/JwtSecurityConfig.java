package kz.teamvictus.poll.security.config;

import kz.teamvictus.poll.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"kz.teamvictus.poll"})
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtSecurityConfig.class);

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    @Autowired
    private JwtSuccessHandler successHandler;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.getSessionCookieConfig().setName("API-Token");
            }
        };

    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        LOGGER.debug("authenticationTokenFilter .... ");
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setRequiresAuthenticationRequestMatcher(new OrRequestMatcher(
                new AntPathRequestMatcher("/client/**"),
                new AntPathRequestMatcher("/expert/**")
        ));
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        http.csrf().disable()
                .headers()
                .frameOptions()
                .sameOrigin()
                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN);

        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/choices").permitAll()
                .antMatchers("/client/**").permitAll()
                .antMatchers("/expert/**").permitAll()
                .antMatchers("/message/**").permitAll()
                .antMatchers("/push/**").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint);

        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}
