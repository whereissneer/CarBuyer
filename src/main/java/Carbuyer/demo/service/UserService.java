package Carbuyer.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.User;

@Service
public interface UserService {
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User getUserById(Long id);
	
}
