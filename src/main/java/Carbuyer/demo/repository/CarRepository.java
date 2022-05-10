package Carbuyer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Carbuyer.demo.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
