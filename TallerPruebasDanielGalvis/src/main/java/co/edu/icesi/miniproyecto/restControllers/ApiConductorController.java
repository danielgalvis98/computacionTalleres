package co.edu.icesi.miniproyecto.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;

@RequestMapping("/api/conductores")
@RestController
public class ApiConductorController {
	ConductoresServicio conducService;
	
	@Autowired
	public ApiConductorController(ConductoresServicio service) {
		conducService = service;
	}
	
	@GetMapping("")
	public Iterable<Tmio1Conductore> getConductores(){
		return conducService.getAllConductores();
	}
	
	@PostMapping("/add")
	public void saveConductor(Tmio1Conductore conductor) throws Exception {
		conducService.addConductor(conductor);
	}

}
