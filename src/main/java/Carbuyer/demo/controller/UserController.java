package Carbuyer.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Carbuyer.demo.entity.User;
import Carbuyer.demo.service.UserService;
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/login")
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
	//user profile page
	@GetMapping("/api/cars/user/{id}")
	public String viewUser(@PathVariable Long id, Principal principal, Model model) {
	//get current user
		User user = userService.getUserByName(principal.getName());
		model.addAttribute("user", user);
		if(user.getId()==id) {
			return "viewUser";
		}
		return "redirect:/api/cars";		
	}
	
	@RequestMapping(value="/api/cars/user/{id}/changePassword", method= {RequestMethod.GET, RequestMethod.PUT})
	public String changePassword(@PathVariable Long id, Model model, Principal principal) {
		User user = userService.getUserByName(principal.getName());
		model.addAttribute("user", user);
		if(user.getId()==id) {
		return "changePasswordForm";
		}
		return "redirect:/api/cars";
	}
	@PostMapping("/api/cars/user/{id}/doChangePassword")
	public String doChangePassword(@PathVariable Long id, @ModelAttribute("user") User user) {
		User existingUser = userService.getUserById(id);
		//System.out.println(existingUser.getPassword());
		//System.err.println(user.getPassword());
		existingUser.setPassword(user.getPassword());
		return "redirect:/api/cars/user/"+id;
		
		
	}
			
}
