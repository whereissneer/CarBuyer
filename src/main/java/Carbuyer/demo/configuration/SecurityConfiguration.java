package Carbuyer.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/styles/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/cars").permitAll()
			.antMatchers(HttpMethod.POST,"/api/cars").hasRole("USER")
			.anyRequest().hasRole("USER")
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll();
	}
	
	
}
