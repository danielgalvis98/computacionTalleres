package co.edu.icesi.miniproyecto.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;

@RequestMapping("/api/conductores")
@RestController
public class ApiConductorController implements IApiConductor{
	ConductoresServicio conducService;
	
	@Autowired
	public ApiConductorController(ConductoresServicio service) {
		conducService = service;
	}
	
	@GetMapping("")
	public Iterable<Tmio1Conductore> getConductores(){
		return conducService.getAllConductores();
	}
	
	@PostMapping("")
	public void saveConductor(@RequestBody Tmio1Conductore conductor) throws Exception {
		conducService.addConductor(conductor);
	}
	
	@GetMapping("/{cedula}")
	public Tmio1Conductore getConductor(@PathVariable String cedula) {
		return conducService.getConductor(cedula);
	}

}
