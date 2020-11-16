package de.tekup.gca.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.tekup.gca.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user = null;
		// (objectMapper()) permet de stocker un objet de type Json dans un objet de type Java 
		// request.getInputStream() permet d'extraire le contenue de l'objet request 
		// User.class pour indiquer que le variable de resultat obtenu est de type User
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		System.out.println("*************");
		System.out.println("login: " +user.getLogin());
		System.out.println("password: " +user.getPassword());
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain,Authentication authResult) throws IOException, ServletException {
		
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
		String jwt = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date (System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256 , SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);

	} 

}
