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

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.TransactionBody;
import co.edu.icesi.miniproyecto.services.RutasServicio;

@RequestMapping("/api/ruta")
@RestController
public class ApiRutaController implements IApiRuta{
	RutasServicio rutaService;
	
	@Autowired
	public ApiRutaController(RutasServicio service) {
		rutaService = service;
	}
	
	@GetMapping("")
	public TransactionBody<Iterable<Tmio1Ruta>> getRutas(){
		TransactionBody<Iterable<Tmio1Ruta>> tb = new TransactionBody<>(); 
		tb.setBody(rutaService.getAllRutas());
		return tb;
	}
	
	@PostMapping("")
	public ResponseEntity<TransactionBody<Tmio1Ruta>> addTmio1Ruta(@RequestBody TransactionBody<Tmio1Ruta> ruta){
		Tmio1Ruta rutaa = ruta.getBody();
		TransactionBody<Tmio1Ruta> tb;
		try {
			rutaService.addRuta(rutaa);
			tb = new TransactionBody<>("NewRuta", rutaa);
			ResponseEntity<TransactionBody<Tmio1Ruta>> response = new ResponseEntity<>(tb,
					HttpStatus.ACCEPTED);
			return response;
		} catch (RuntimeException e) {
			tb = new TransactionBody<>(e.getMessage(), rutaa);
			ResponseEntity<TransactionBody<Tmio1Ruta>> response = new ResponseEntity<>(tb,
					HttpStatus.MULTI_STATUS);
			return response;
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionBody<Tmio1Ruta>> getRuta(@PathVariable Integer id) {
		Tmio1Ruta ruta = rutaService.getRuta(id);
		TransactionBody<Tmio1Ruta> tb = new TransactionBody<>("ActRuta", ruta);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

}
