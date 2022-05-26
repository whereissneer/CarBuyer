package Carbuyer.demo.controller;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
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
		
		
		/*@GetMapping("/api/cars")
		public String getCars(Model model, Principal principal) {
			model.addAttribute("cars", carService.getAllCars());
			if(principal!=null) {
				model.addAttribute("user", userService.getUserByName(principal.getName()));
			}
			return "cars";
		}*/
		
		//TODO
		//ADD OWNER MAPPING TO DISPLAY IN CARD BODY
		
		@GetMapping({"/api/cars/search", "/api/cars"})
		public String getCarsByKeyword(Car car, Model model, String keyword, Principal principal) {
			boolean nothingWasFound = false;
			//get car
			//Car thisCar = carService.getCarById(car.getId());
			//get owner
			//User owner = userService.getUserById(thisCar.getOwner().getId());
			//model.addAttribute("owner", owner);
			if(keyword!=null) {
				List<Car> cars = carService.getCarByKeyword(keyword);
				model.addAttribute("cars", cars);
				
				if(cars.size()==0) {
					nothingWasFound=true;
					model.addAttribute("nothingWasFound", nothingWasFound);
				}
			}
			else {
				model.addAttribute("cars", carService.getAllCars());
			}
			if(principal!=null) {
				model.addAttribute("user", userService.getUserByName(principal.getName()));
			}
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
			if(principal!=null) {
				User user = userService.getUserByName(principal.getName());
			//get car
				Car thisCar = carService.getCarById(id);
				//get owner
				User owner = userService.getUserById(thisCar.getOwner().getId());
				if(user.equals(owner)) {
					canDelete=true;
				}
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
		//PUT MAPPING
		@RequestMapping(value="/api/cars/edit/{id}" ,method= {RequestMethod.GET})
		public String editOffer(@PathVariable Long id, Principal principal, @ModelAttribute("car") Car car) {
			//get current user
			User user = userService.getUserByName(principal.getName());
			//get car
			Car thisCar = carService.getCarById(id);
			//get owner
			User owner = userService.getUserById(thisCar.getOwner().getId());
			if(user.equals(owner)) {
				return "editForm";
			}
			else {
				return"redirect:/api/cars";
			}
		}
		@PostMapping("/api/cars/editCar/{id}")
		public String editCar(@PathVariable Long id, @ModelAttribute("car") Car car, Model model) {
			Car existingCar = carService.getCarById(id);
			existingCar.setId(id);
			existingCar.setCarTitle(car.getCarTitle());
			existingCar.setCarPhotoUrl(car.getCarPhotoUrl());
			existingCar.setYearModel(car.getYearModel());
			existingCar.setMileage(car.getMileage());
			existingCar.setEngineType(car.getEngineType());
			existingCar.setEngineCapacity(car.getEngineCapacity());
			existingCar.setEnginePower(car.getEnginePower());
			existingCar.setGearboxType(car.getGearboxType());
			existingCar.setColour(car.getColour());
			existingCar.setDriveType(car.getDriveType());
			existingCar.setIsDamaged(car.getIsDamaged());
			existingCar.setPrice(car.getPrice());
			carService.saveCar(existingCar);
			return "redirect:/api/cars";
		}
		
		
}
