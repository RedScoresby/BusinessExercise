package com.red.exercise.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.red.exercise.filter.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(myUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate", "/login").permitAll()						//here you can access without being authenticated
		.antMatchers("/h2-console/**", "/api/open/**", "/resources/**").permitAll() 				//so you can access the h2 data base
		.antMatchers("/employee/delete/**", "/employee/new", "/employee/modify/**", "/employee/update", 
				"/department/delete/**", "/department/new", "/department/modify/**", "/department/update",
				"/role/delete/**", "/role/new", "/role/modify/**", "/role/update").hasRole("ADMIN")	//here you can access only when you are administrator
		.anyRequest().authenticated()																//here you can access only when you are authenticated
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);								
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		//Because all the passwords are actually "" we don't need to make this method more complicated, nor encrypt the string that we receive as the password
		return NoOpPasswordEncoder.getInstance();
	}

	
}
