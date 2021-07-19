package com.red.exercise.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.red.exercise.DAO.EmployeeDAO;
import com.red.exercise.DAO.RoleDAO;
import com.red.exercise.model.Employee;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public UserDetails loadUserByUsername(String itsActuallyTheDNI) throws UsernameNotFoundException
	{
		Employee employee = employeeDAO.findByDNI(itsActuallyTheDNI).orElse(null);
		
		UserBuilder builder = null;
		
		if (employee != null)
		{
			builder = User.withUsername(itsActuallyTheDNI);
			builder.disabled(false);
			builder.password("");
			
			if (employee.getRole().getId() == roleDAO.findAdmin().getId())
			{
				builder.authorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			else
			{
				builder.authorities(new SimpleGrantedAuthority("ROLE_NORMAL"));
			}
		}
		else
		{
			throw new UsernameNotFoundException("Employee not found");
		}
		
		return builder.build();
	}

}
