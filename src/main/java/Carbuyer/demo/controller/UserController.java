package Carbuyer.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Carbuyer.demo.entity.User;
import Carbuyer.demo.service.UserService;
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/api/cars/login")
	public String loginForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "loginUser";
	}
	
	@GetMapping("/api/cars/createNewUser")
	public String createNewUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "createNewUserForm";
	}
	@PostMapping("/api/cars/addNewUser")
	public String addNewUser(@ModelAttribute User user) {
		userService.saveUser(user);
		return "redirect:/login";
	}
}
