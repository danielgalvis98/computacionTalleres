package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.TransactionBody;
import co.edu.icesi.miniproyecto.services.BusesServicio;

@RestController
public class ApiBusesController implements IApiBuses{
	BusesServicio busService;
	
	@Autowired
	public ApiBusesController(BusesServicio service) {
		busService = service;
	}
	
	@GetMapping("/api/buses")
	public TransactionBody<Iterable<Tmio1Bus>> getBuses(){
		TransactionBody<Iterable<Tmio1Bus>> tb= new TransactionBody<>();
		tb.setBody(busService.getAllBuses());
		
		return tb;
	}
	
	@PostMapping("/api/buses")
	public ResponseEntity<TransactionBody<Tmio1Bus>> addTmio1Bus(@RequestBody TransactionBody<Tmio1Bus> bus) {
		Tmio1Bus buss = bus.getBody();
		busService.addBus(buss);
		TransactionBody<Tmio1Bus> tb = new TransactionBody<Tmio1Bus>("NewBus", buss);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = new ResponseEntity<>(tb,
				HttpStatus.SEE_OTHER);
		return response;
	}
	
	@GetMapping("/api/buses/{id}")
	public ResponseEntity<TransactionBody<Tmio1Bus>> getBus(@PathVariable Integer id) {
		Tmio1Bus bus = busService.getBus(id);
		TransactionBody<Tmio1Bus> tb = new TransactionBody<>("NewBus", bus);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = new ResponseEntity<>(tb,
				HttpStatus.SEE_OTHER);
		
		return response;
	}

}