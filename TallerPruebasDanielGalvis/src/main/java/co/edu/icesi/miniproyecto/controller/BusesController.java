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
import co.edu.icesi.miniproyecto.services.BusesServicio;

@RequestMapping("/bus")
@Controller
public class BusesController {
	BusesServicio busService;
	
	@Autowired
	public BusesController(BusesServicio service) {
		busService = service;
	}
	
	@GetMapping("")
	public String indexBuses(Model model) {
		model.addAttribute("buses", busService.getAllBuses());
		return "buses/index";
	}
	
	@GetMapping("/add")
	public String addBus(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		
		return "buses/add";
	}
	
	@PostMapping("/add")
	public String saveBus(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Bus bus, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) 
				return "buses/add";
			else
				busService.addBus(bus);
		}
		
		return "redirect:/bus";
	}

}
