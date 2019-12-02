package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiSitios {
	public TransactionBody<Iterable<Tmio1Sitio>> getSitios();
	
	public ResponseEntity<TransactionBody<Tmio1Sitio>> addTmio1Sitio(TransactionBody<Tmio1Sitio> sitio);
	
	public ResponseEntity<TransactionBody<Tmio1Sitio>> getSitio(Long id);
	
	public ResponseEntity<TransactionBody<Tmio1Sitio>> updateSitio(TransactionBody<Tmio1Sitio> sitio);
	
	public ResponseEntity<TransactionBody<Tmio1Sitio>> deleteSitio(@RequestBody TransactionBody<Tmio1Sitio> sitio);
}
