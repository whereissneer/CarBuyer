package Carbuyer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Carbuyer.demo.entity.Car;
import Carbuyer.demo.entity.User;
import Carbuyer.demo.service.CarService;
import Carbuyer.demo.service.UserService;

@Controller
public class CarController {

		private CarService carService;
		public CarController(CarService carService) {
			super();
			this.carService = carService;
		};
		
		@GetMapping("/api/cars")
		public String getCars(Model model) {
			model.addAttribute("cars", carService.getAllCars());
			return "cars";
		}
		
		@GetMapping("/api/cars/new")
		public String createNewCarOfferForm(Model model) {
			Car car = new Car();
			model.addAttribute("car", car);
			return "createNewOfferForm";
		}
		
		@PostMapping("/api/cars")
		public String saveCar(@ModelAttribute("car") Car car) {
			carService.saveCar(car);
			return "redirect:/api/cars";
		}
		
		@GetMapping("/api/cars/view/{id}")
		public String viewOffer(@PathVariable Long id, Model model) {
			model.addAttribute("car", carService.getCarById(id));
			return "viewOffer";
		}
		
}
