package Carbuyer.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.Car;

@Service
public interface CarService {
	List<Car> getAllCars();
}
