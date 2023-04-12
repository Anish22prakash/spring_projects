package com.doobi.securityConfiguration;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("indside dofilter.....");
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			
			
			SecretKey key= Keys.hmacShaKeyFor(SecurityContants.JWT_KEY.getBytes());
			
			String jwt= Jwts.builder()
					   .setIssuer("Anish")
					   .setSubject("JWT TOKEN")
					   .claim("mobile_number", authentication.getName())
					   .claim("authorities", populateAuthorities(authentication.getAuthorities()))
					   .setIssuedAt(new Date())
					   .setExpiration(new Date(new Date().getTime()+3000000))
					   .signWith(key).compact();
			
			response.setHeader(SecurityContants.JWT_HEADER, jwt);
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}
	
	 private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
	        
	    	Set<String> authoritiesSet = new HashSet<>();
	        
	        for (GrantedAuthority authority : collection) {
	            authoritiesSet.add(authority.getAuthority());
	        }
	        return String.join(",", authoritiesSet);
	   
	    
	    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
        return !request.getServletPath().equals("/logIn");	
	}
	

}
