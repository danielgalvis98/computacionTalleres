package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.services.BusesServicio;

@RestController
public class ApiBusesController implements IApiBuses{
	BusesServicio busService;
	
	@Autowired
	public ApiBusesController(BusesServicio service) {
		busService = service;
	}
	
	@GetMapping("/api/buses")
	public Iterable<Tmio1Bus> getBuses(){
		return busService.getAllBuses();
	}
	
	@PostMapping("/api/buses")
	public Tmio1Bus addTmio1Bus(@RequestBody Tmio1Bus bus) {
		busService.addBus(bus);
		return bus;
	}
	
	@GetMapping("/api/buses/{id}")
	public Tmio1Bus getBus(@PathVariable Integer id) {
		return busService.getBus(id);
	}

}
