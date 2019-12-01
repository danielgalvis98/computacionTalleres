package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiBuses {

	public TransactionBody<Iterable<Tmio1Bus>> getBuses();
	
	public ResponseEntity<TransactionBody<Tmio1Bus>> addTmio1Bus(TransactionBody<Tmio1Bus> bus);
	
	public ResponseEntity<TransactionBody<Tmio1Bus>> getBus(Integer id);
}
