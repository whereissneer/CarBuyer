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


	@Override
	public Car getCarById(Long id) {
		return carRepository.getById(id);
	}


	@Override
	public void deleteById(Long id) {
		carRepository.deleteById(id);
		
	}

}
