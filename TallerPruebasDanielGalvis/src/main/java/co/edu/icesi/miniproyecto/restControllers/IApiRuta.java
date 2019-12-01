package co.edu.icesi.miniproyecto.restControllers;
import org.springframework.http.ResponseEntity;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiRuta {
	public TransactionBody<Iterable<Tmio1Ruta>> getRutas();
	
	public ResponseEntity<TransactionBody<Tmio1Ruta>> addTmio1Ruta(TransactionBody<Tmio1Ruta> ruta);
	
	public ResponseEntity<TransactionBody<Tmio1Ruta>> getRuta(Integer id);

}
