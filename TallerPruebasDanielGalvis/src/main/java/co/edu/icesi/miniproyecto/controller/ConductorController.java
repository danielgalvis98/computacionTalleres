package co.edu.icesi.miniproyecto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;

@RequestMapping("/conductor")
@Controller
public class ConductorController {
	ConductoresServicio conducService;
	
	@Autowired
	public ConductorController(ConductoresServicio service) {
		conducService = service;
	}
	
	@GetMapping("")
	public String indexConductores(Model model) {
		model.addAttribute("conductores", conducService.getAllConductores());
		return "conductores/index";
	}
	
	@GetMapping("/add")
	public String addConductor(Model model) {
		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		
		return "conductores/add";
	}
	
	@PostMapping("/add")
	public String saveConductor(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Conductore conductor, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) 
				return "conductores/add";
			else {
				try {
					conducService.addConductor(conductor);
				} catch (Exception e) {
					model.addAttribute("exception", e.getMessage());
					return "conductores/add";
				}
				
			}
		}
		
		return "redirect:/conductor";
	}

}
