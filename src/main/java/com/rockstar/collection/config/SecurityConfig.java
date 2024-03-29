package com.rockstar.collection.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Value(value = "${auth0.audience}")
    private String audience;
	
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
    		logger.info("Configuring for Jwt token with issuer {} & audience {}", issuer, audience);
    	
        JwtWebSecurityConfigurer
                .forRS256(audience, issuer)
                .configure(http)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/collections/search/**").hasAuthority("search:collections")
                .antMatchers(HttpMethod.GET, "/collections/**").hasAuthority("read:collections")
                .antMatchers(HttpMethod.POST, "/collections/**").hasAuthority("create:collections")
                .antMatchers(HttpMethod.PUT, "/collections/**").hasAuthority("update:collections")
                .antMatchers(HttpMethod.DELETE, "/collections/**").hasAuthority("delete:collections")
                .anyRequest().authenticated();
    }
}
