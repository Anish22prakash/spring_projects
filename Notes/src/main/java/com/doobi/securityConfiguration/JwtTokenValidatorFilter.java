package com.doobi.securityConfiguration;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidatorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String jwt=request.getHeader(SecurityContants.JWT_HEADER);
		
		if(jwt!=null) {
			try {
				jwt=jwt.substring(7);
				SecretKey key= Keys.hmacShaKeyFor(SecurityContants.JWT_KEY.getBytes());
				
				Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				String mobile_num= String.valueOf(claims.get("mobile_number"));
				
				String authorities= (String)claims.get("authorities");	
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				
				Authentication auth = new UsernamePasswordAuthenticationToken(mobile_num, null, auths);
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
				
				
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token recevied");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	
	//this time this validation filter has to be executed for all the apis except the /login api
	
		@Override
		protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
			return request.getServletPath().equals("/logIn");
		}

}
