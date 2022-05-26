package Carbuyer.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/styles/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/cars").permitAll()
			.antMatchers(HttpMethod.GET, "/api/cars/login").permitAll()
			.antMatchers(HttpMethod.GET, "/api/cars/view/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/cars/createNewUser").permitAll()
			.antMatchers(HttpMethod.POST, "/api/cars/addNewUser").permitAll()
			.antMatchers(HttpMethod.GET, "/api/cars/search").permitAll()
			.antMatchers(HttpMethod.POST,"/api/cars").hasRole("USER")
			.anyRequest().hasRole("USER")
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/api/cars").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
			.invalidateHttpSession(true);
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	@Bean 
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
