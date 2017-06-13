package com.cmrh.auth.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private Filter ssoFilter;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .antMatcher("/**").authorizeRequests()
            .antMatchers("/", "/login**", "/webjars/**").permitAll()
            .anyRequest().authenticated()
        .and()
        	.exceptionHandling()
			.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login/github")).and().logout()
        .and()
            //.formLogin()
        	.addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
        // @formatter:on
    }
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("123456").roles("USER").build());
		return manager;
	}
}
