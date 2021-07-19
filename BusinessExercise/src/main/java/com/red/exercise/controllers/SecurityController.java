package com.red.exercise.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.red.exercise.filter.EmployeeFilter;
import com.red.exercise.security.AuthenticationRequest;
import com.red.exercise.security.MyUserDetailsService;
import com.red.exercise.services.EmployeeService;
import com.red.exercise.utils.JwtUtil;
import com.red.exercise.utils.ModelAndViewCreator;

@Controller
public class SecurityController
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping ("/authenticate")
	//ResponseEntity<AuthenticationResponse>
	public ModelAndView createAuthenticationToken(AuthenticationRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), ""));
		}
		catch (BadCredentialsException e)
		{
			return ModelAndViewCreator.createModelAndView("wrongDNI", true, "generalFolder/login");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		String jwt = JwtUtil.generateToken(userDetails);
		
		//We add the jwt to the session to check if the user is logged in and what level of authorization it has
		request.getSession().setAttribute("jwt", "jwt: " + jwt);
		request.getSession().setAttribute("isAdmin", userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
		
		//We also add the user to the session because we may need it later on
		request.getSession().setAttribute("user", employeeService.getEmployeeByDNI(userDetails.getUsername()));
		
		//We return the list of all employees and add if it's admin so we can show it the special operations
		return ModelAndViewCreator.createModelAndView("listOfAllEmployees", employeeService.getAllEmployees(), "employeeFolder/employeeList")
				.addObject("employeeFilter", new EmployeeFilter());
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
