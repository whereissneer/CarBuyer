package Carbuyer.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Carbuyer.demo.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	
	@Query(value="SELECT * FROM car WHERE car_title like %:keyword%", nativeQuery = true)
	List<Car> findCarByKeyword(@Param("keyword") String keyword);

}
