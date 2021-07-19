package com.red.exercise.security;

public class AuthenticationRequest
{
	private String username;

	public AuthenticationRequest()
	{
		
	}
	
	public AuthenticationRequest(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
}
