package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.TransactionBody;
import co.edu.icesi.miniproyecto.services.SitiosServicio;

@RestController
public class ApiSitiosController implements IApiSitios {
	SitiosServicio sitioService;
	
	@Autowired
	public ApiSitiosController(SitiosServicio sitioService) {
		this.sitioService=sitioService;
	}
	
	@GetMapping("/api/sitios")
	@Override
	public TransactionBody<Iterable<Tmio1Sitio>> getSitios() {
		TransactionBody<Iterable<Tmio1Sitio>> tb= new TransactionBody<>();
		tb.setBody(sitioService.getAllSitios());
		
		return tb;
	}
	
	@PostMapping("/api/sitios")
	@Override
	public ResponseEntity<TransactionBody<Tmio1Sitio>> addTmio1Sitio(@RequestBody TransactionBody<Tmio1Sitio> sitio) {
		Tmio1Sitio sitios = sitio.getBody();
		sitioService.addSitio(sitios);
		TransactionBody<Tmio1Sitio> tb = new TransactionBody<Tmio1Sitio>("NewSitio",sitios);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}
	
	@GetMapping("/api/sitios/{id}")
	@Override
	public ResponseEntity<TransactionBody<Tmio1Sitio>> getSitio(@PathVariable Long id) {
		Tmio1Sitio sitio = sitioService.getSitio(id);
		TransactionBody<Tmio1Sitio> tb = new TransactionBody<>("getSitio",sitio);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@PutMapping("/api/sitios")
	@Override
	public ResponseEntity<TransactionBody<Tmio1Sitio>> updateSitio(@RequestBody TransactionBody<Tmio1Sitio> sitio) {
		Tmio1Sitio sitios =sitio.getBody();
		sitioService.updateSitio(sitios);
		TransactionBody<Tmio1Sitio> tb = new TransactionBody<>("uptSitio",sitios);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/api/sitios")
	@Override
	public ResponseEntity<TransactionBody<Tmio1Sitio>> deleteSitio(TransactionBody<Tmio1Sitio> sitio) {
		Tmio1Sitio sitios =sitio.getBody();
		try {
			sitioService.removeSitio(sitios);
			TransactionBody<Tmio1Sitio> tb = new TransactionBody<>("DelServ",sitios);
			ResponseEntity<TransactionBody<Tmio1Sitio>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
		}catch(Exception e) {
			TransactionBody<Tmio1Sitio> tb = new TransactionBody<>("DelServ",sitios);
			ResponseEntity<TransactionBody<Tmio1Sitio>> response = new ResponseEntity<> (tb,
					HttpStatus.PRECONDITION_FAILED);
			return response;
		}
		
	}

}
