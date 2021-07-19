package com.red.exercise.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil
{
	private static String SECRET_KEY ="%C*F-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&E)H@McQfTjWnZr4u7";
	private static int EXPIRATION_TIME = 1000 * 60 * 60 * 2;	//2 hours
	
	public static String extractUsername (String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	
	public static Date extractExpiration (String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}
	
	public static <T> T extractClaim (String token, Function<Claims, T> claimsResolver)
	{
		Claims claims = extractAllClaims (token);
		return claimsResolver.apply(claims);
	}
	
	private static Claims extractAllClaims (String token)
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public static String generateToken (UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		return createToken (claims, userDetails.getUsername());
	}
	
	private static String createToken (Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
	public static Boolean validateToken (String token, UserDetails userDetails) 
	{
		final String username = extractUsername(token);
		return (username.equals((userDetails.getUsername())) && !isTokenExpired(token));
	}
	
	private static Boolean isTokenExpired (String token)
	{
		return extractExpiration(token).before(new Date());
	}
}
