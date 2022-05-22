package Carbuyer.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Carbuyer.demo.entity.User;

public class UserDetailsService implements UserDetails{
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	private User user;
	
	public UserDetailsService() {
		super();
	}

	public UserDetailsService(User user){
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.authorities = Arrays.stream(user.getRoles().split(","))
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
