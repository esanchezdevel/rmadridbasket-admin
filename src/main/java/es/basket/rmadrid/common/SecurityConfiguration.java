package es.basket.rmadrid.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_LIST = {"/Admin"};
	
	/*
	 * Authentication method
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("api-user")
			.password(passwordEncoder().encode("password"))
			.roles("USER")
			.and()
				.withUser("admin")
			.password(passwordEncoder().encode("admin"))
			.roles("USER", "ADMIN");
	}
	
	/*
	 * Authorization method
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.csrf().disable().authorizeRequests()
		.and()
		.authorizeRequests()
		.antMatchers(AUTH_LIST).authenticated()
		.and()
		.httpBasic();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
