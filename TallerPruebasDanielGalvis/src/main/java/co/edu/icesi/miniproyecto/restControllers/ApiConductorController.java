package co.edu.icesi.miniproyecto.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.TransactionBody;
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
	public TransactionBody<Iterable<Tmio1Conductore>> getConductores(){
		TransactionBody<Iterable<Tmio1Conductore>> tb= new TransactionBody<>();
		tb.setBody(conducService.getAllConductores());
		
		return tb;
	}
	
	@PostMapping("")
	public ResponseEntity<TransactionBody<Tmio1Conductore>> saveConductor(@RequestBody TransactionBody<Tmio1Conductore> conductor) {
		Tmio1Conductore conductore = conductor.getBody();
		TransactionBody<Tmio1Conductore> tb;
		try {
			conducService.addConductor(conductore);
			tb = new TransactionBody<>("NewConduc", conductore);
			ResponseEntity<TransactionBody<Tmio1Conductore>> response = new ResponseEntity<>(tb,
					HttpStatus.SEE_OTHER);
			return response;
		} catch (RuntimeException e) {
			tb = new TransactionBody<>(e.getMessage(), conductore);
			ResponseEntity<TransactionBody<Tmio1Conductore>> response = new ResponseEntity<>(tb,
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@GetMapping("/{cedula}")
	public ResponseEntity<TransactionBody<Tmio1Conductore>> getConductor(@PathVariable String cedula) {
		Tmio1Conductore conduc = conducService.getConductor(cedula);
		TransactionBody<Tmio1Conductore> tb = new TransactionBody<>("ActConduc", conduc);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = new ResponseEntity<>(tb,
				HttpStatus.SEE_OTHER);
		return response;
	}

}
