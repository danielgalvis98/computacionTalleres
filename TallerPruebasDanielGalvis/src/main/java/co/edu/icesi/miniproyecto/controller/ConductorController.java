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

import co.edu.icesi.miniproyecto.delegate.ConductorDelegate;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@RequestMapping("/conductor")
@Controller
public class ConductorController {
	ConductorDelegate conductorDelegate;
	
	@Autowired
	public ConductorController(ConductorDelegate service) {
		conductorDelegate = service;
	}
	
	@GetMapping("")
	public String indexConductores(Model model) {
		model.addAttribute("conductores", conductorDelegate.getAllConductores());
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
					conductorDelegate.addConductor(conductor);
				} catch (Exception e) {
					//e.printStackTrace();
					model.addAttribute("exception", e.getMessage());
					return "conductores/add";
				}
				
			}
		}
		
		return "redirect:/conductor";
	}

}
