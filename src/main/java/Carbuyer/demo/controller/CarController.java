package Carbuyer.demo.controller;

import java.lang.reflect.Method;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Carbuyer.demo.entity.Car;
import Carbuyer.demo.entity.User;
import Carbuyer.demo.repository.CarRepository;
import Carbuyer.demo.repository.UserRepository;
import Carbuyer.demo.service.CarService;
import Carbuyer.demo.service.UserDetailsService;
import Carbuyer.demo.service.UserService;

@Controller
public class CarController {

		@Autowired
		private CarService carService;
		
		@Autowired
		private UserService userService;	
		
		
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
		
		@PostMapping("/api/cars/addCar")
		public String saveCar(@ModelAttribute("car") Car car, Principal principal) {
			User user = userService.getUserByName(principal.getName());
			car.setOwner(user);
			carService.saveCar(car);
			return "redirect:/api/cars";
		}
		
		@GetMapping("/api/cars/view/{id}")
		public String viewOffer(@PathVariable Long id, Model model, Principal principal) {
			boolean canDelete = false;
			//get current user
			User user = userService.getUserByName(principal.getName());
			//get car
			Car thisCar = carService.getCarById(id);
			//get owner
			User owner = userService.getUserById(thisCar.getOwner().getId());
			if(user.equals(owner)) {
				canDelete=true;
			}
			model.addAttribute("car", carService.getCarById(id));
			model.addAttribute("canDelete", canDelete);
			return "viewOffer";
		}
		
		@RequestMapping(value="/api/cars/delete/{id}" ,method= {RequestMethod.GET, RequestMethod.DELETE})
		public String deleteOffer(@PathVariable Long id, Principal principal) {
			//get current user
			User user = userService.getUserByName(principal.getName());
			//get car
			Car thisCar = carService.getCarById(id);
			//get owner
			User owner = userService.getUserById(thisCar.getOwner().getId());
			if(user.equals(owner)) {
				carService.deleteById(id);
			}
			return "redirect:/api/cars";
		}
		
}
