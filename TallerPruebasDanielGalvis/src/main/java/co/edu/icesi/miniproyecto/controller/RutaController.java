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

import co.edu.icesi.miniproyecto.delegate.RutaDelegate;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@RequestMapping("/ruta")
@Controller
public class RutaController {
	RutaDelegate rutaDelegate;
	
	@Autowired
	public RutaController(RutaDelegate service) {
		rutaDelegate = service;
	}
	
	@GetMapping("")
	public String indexRutas(Model model) {
		model.addAttribute("rutas", rutaDelegate.getAllRutas());
		return "rutas/index";
	}
	
	@GetMapping("/add")
	public String addRuta(Model model) {
		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		
		return "rutas/add";
	}
	
	@PostMapping("/add")
	public String saveRuta(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Ruta ruta, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) 
				return "rutas/add";
			else
				try {
					rutaDelegate.addTmio1Ruta(ruta);
				} catch (Exception e) {
					model.addAttribute("exception", e.getMessage());
					return "rutas/add";
				}
		}
		
		return "redirect:/ruta";
	}

}
