package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.model.TransactionBody;
import co.edu.icesi.miniproyecto.services.SitiosRutaService;

@RequestMapping("/api/sitioruta")
@RestController
public class ApiSitioRutaController implements IApiSitioRuta {

	private SitiosRutaService sitiosRuta;
	
	@Autowired
	public ApiSitioRutaController(SitiosRutaService sitiosRuta) {
		this.sitiosRuta = sitiosRuta;
	}
	
	@Override
	@GetMapping("")
	public TransactionBody<Iterable<Tmio1SitiosRuta>> getSitioRutas() {
		TransactionBody<Iterable<Tmio1SitiosRuta>> tb = new TransactionBody<>();
		tb.setBody(sitiosRuta.getAllSitiosRuta());
		return tb;
	}

	@Override
	@PostMapping("")
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> saveSitioRuta(TransactionBody<Tmio1SitiosRuta> sitioRuta) {
		Tmio1SitiosRuta sitioRutaBody = sitioRuta.getBody();
		TransactionBody<Tmio1SitiosRuta> tb;
		try {
			sitiosRuta.addSitioRuta(sitioRutaBody);
			tb = new TransactionBody<>("New sitio-ruta", sitioRutaBody);
			ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
		} catch (RuntimeException e) {
			tb = new TransactionBody<>(e.getMessage(), sitioRutaBody);
			ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = new ResponseEntity<> (tb,
					HttpStatus.MULTI_STATUS);
			return response;
		}
	}

	@Override
	@GetMapping("/{idSitio}/{idRuta}")
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> getSitioRuta(@PathVariable("idSitio") int idSitio,@PathVariable("idRuta") int idRuta) {
		Tmio1SitiosRutaPK key = new Tmio1SitiosRutaPK();
		key.setIdRuta(idRuta);
		key.setIdSitio(idSitio);
		Tmio1SitiosRuta sitioRuta = sitiosRuta.getSitioRuta(key);
		TransactionBody<Tmio1SitiosRuta> tb = new TransactionBody<>("ActSitioRuta", sitioRuta);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = new ResponseEntity<> (tb, HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@DeleteMapping("")
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> deleteSitioRuta(
			TransactionBody<Tmio1SitiosRuta> sitioRuta) {
		Tmio1SitiosRuta sitRut = sitioRuta.getBody();
		sitiosRuta.removeSitioRuta(sitRut);
		TransactionBody<Tmio1SitiosRuta> tb = new TransactionBody<>("DelSitRut", sitRut);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = new 
				ResponseEntity<> (tb, HttpStatus.ACCEPTED);
		return response;
	}

}
