package Carbuyer.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.Car;

@Service
public interface CarService {
	List<Car> getAllCars();
	
	Car saveCar(Car car);
	
	Car getCarById(Long id);
	
	void deleteById(Long id);
	
	List<Car> getCarByKeyword(String keyword);
}
