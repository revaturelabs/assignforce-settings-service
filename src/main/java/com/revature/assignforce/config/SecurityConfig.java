package com.revature.assignforce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* 
 * SecurityConfig check all authorize requests and gives permitions 
 *
 */
@Configuration
@EnableSwagger2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    /*
     * Below we create method "configure" with parameters (HttpSecurity http) that tells us:
     * - any request to our application requires the user to be authenticated
	 * - users will be authenticated with form based login
	 * - users will be authenticated with HTTP Basic authentication     
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }
}
