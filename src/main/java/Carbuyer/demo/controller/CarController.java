package Carbuyer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Carbuyer.demo.service.CarService;

@Controller
public class CarController {

		private CarService carService;

		public CarController(CarService carService) {
			super();
			this.carService = carService;
		};
		
		@GetMapping("/api/cars")
		public String getCars(Model model) {
			model.addAttribute("car", carService.getAllCars());
			return "cars";
		}
}
