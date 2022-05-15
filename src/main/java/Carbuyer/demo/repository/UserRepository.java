package Carbuyer.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Carbuyer.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
