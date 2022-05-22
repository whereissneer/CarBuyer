package Carbuyer.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.User;
import Carbuyer.demo.repository.UserRepository;
import Carbuyer.demo.service.UserDetailsService;
import Carbuyer.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
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
