package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiConductor {

	public TransactionBody<Iterable<Tmio1Conductore>> getConductores();
	
	public ResponseEntity<TransactionBody<Tmio1Conductore>> saveConductor(TransactionBody<Tmio1Conductore> conductor);
	
	public ResponseEntity<TransactionBody<Tmio1Conductore>> getConductor(String cedula);
}
