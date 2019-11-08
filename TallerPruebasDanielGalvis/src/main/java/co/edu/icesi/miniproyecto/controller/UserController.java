package co.edu.icesi.miniproyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.miniproyecto.services.Tmio1UserService;

@Controller
public class UserController {
	Tmio1UserService service;
	
	@Autowired
	public UserController(Tmio1UserService service) {
		this.service = service;
	}
	
	@GetMapping("/login")
	public String login() {
		return "customlogin";
	}

}
