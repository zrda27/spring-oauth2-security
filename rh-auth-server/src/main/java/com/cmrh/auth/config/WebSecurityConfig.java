package com.cmrh.auth.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Filter ssoFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**/*.html").permitAll()	//不拦截/resources/static/目录下的资源
				.anyRequest().authenticated()
				.and()
			.formLogin()	//使用表单登录
				.loginPage("/login.html")
				.defaultSuccessUrl("/index.html")
				.permitAll()
				.and()
			.httpBasic()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index.html")
				.invalidateHttpSession(true)
				.and()
			.addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("123456").roles("USER").build());
		return manager;
	}
}
