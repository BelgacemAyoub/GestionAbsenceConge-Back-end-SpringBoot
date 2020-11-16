
package de.tekup.gca.security;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles(
		 * "ADMIN", "USER", "DIRECTEUR")
		 * .and().withUser("user").password("{noop}user").roles("USER")
		 * .and().withUser("directeur").password("{noop}directeur").roles("USER",
		 * "DIRECTEUR");
		 */
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// permet de s'authentifier en dehors de la page login fourni par spring security
		http.csrf().disable();
		// pour désactiver l'authentification avec session(cookies)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// http.formLogin();
//		http.authorizeRequests().antMatchers("/api/user/addUser", "/api/conge/*", "/api/absence/*").hasRole("USER");
//		http.authorizeRequests().antMatchers("/api/reclamation/addReclamation").hasRole("USER");
//		http.authorizeRequests().antMatchers("/api/message/addMessage").hasRole("USER");
//
//		http.authorizeRequests().antMatchers("/api/user/addUser", "/api/conge/*", "/api/absence/*")
//				.hasRole("DIRECTEUR");
//		http.authorizeRequests().antMatchers("/api/reclamation/list", "/findReclamation", "/findReclamationsById",
//				"/reclamationResponse").hasRole("DIRECTEUR");
//
//		http.authorizeRequests().antMatchers("/**").permitAll();
		
		//http.authorizeRequests().antMatchers("api/user/list").hasRole("USER");
		http.authorizeRequests().antMatchers("/login/**", "/api/user/addUser/**").permitAll();
		http.authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority("ADMIN", "DIRECTEUR");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

	}
}
