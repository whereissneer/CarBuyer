package Carbuyer.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.User;
import Carbuyer.demo.repository.UserRepository;
import Carbuyer.demo.service.UserDetailsService;
import Carbuyer.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		User userToSave = new User();
		userToSave.setId(user.getId());
		userToSave.setUsername(user.getUsername());
		userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
		userToSave.setRoles(user.getRoles());
		userToSave.setCars(null);
		return userRepository.save(userToSave);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getById(id);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		user.orElseThrow(()->new UsernameNotFoundException("Not found: "+username));
		return user.map(UserDetailsService::new).get();
	}

	@Override
	public User getUserByName(String name) {
		return userRepository.findByUsername(name);
	}

	
	

	
}
