package Carbuyer.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.User;
import Carbuyer.demo.repository.UserRepository;
import Carbuyer.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

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

	
}
