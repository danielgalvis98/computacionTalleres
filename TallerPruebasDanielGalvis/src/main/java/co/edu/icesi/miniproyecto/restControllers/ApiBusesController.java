package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.services.BusesServicio;

@RequestMapping("/api/buses")
@RestController
public class ApiBusesController {
	BusesServicio busService;
	
	@Autowired
	public ApiBusesController(BusesServicio service) {
		busService = service;
	}
	
	@GetMapping("")
	public Iterable<Tmio1Bus> getBuses(){
		return busService.getAllBuses();
	}
	
	@PostMapping("/add")
	public void saveBus(Tmio1Bus bus) {
		busService.addBus(bus);
	}
		

}
