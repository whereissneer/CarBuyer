package Carbuyer.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Carbuyer.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	Optional<User> findById(Long id);
}
