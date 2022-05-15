package Carbuyer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Carbuyer.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
