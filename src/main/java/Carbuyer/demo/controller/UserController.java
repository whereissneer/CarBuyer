package Carbuyer.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
