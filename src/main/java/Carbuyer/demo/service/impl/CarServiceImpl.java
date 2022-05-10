package Carbuyer.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import Carbuyer.demo.entity.Car;
import Carbuyer.demo.repository.CarRepository;
import Carbuyer.demo.service.CarService;

@Service
public class CarServiceImpl implements CarService{

	private CarRepository carRepository;
	
	
	public CarServiceImpl(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}


	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}


	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

}
