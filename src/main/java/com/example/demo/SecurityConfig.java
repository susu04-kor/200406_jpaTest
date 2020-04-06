package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http.authorizeRequests()	
		.mvcMatchers("/","/all/**").permitAll()	
		.mvcMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		//http.formLogin();	
		//http.formLogin().loginPage("/login").permitAll();
		
		//http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//.invalidateHttpSession(true);
		
		http.formLogin().successForwardUrl("/list");
		http.logout().logoutSuccessUrl("/login");
		
		http.httpBasic();	
		
		http.csrf().disable();
		
	}

	
	
}
