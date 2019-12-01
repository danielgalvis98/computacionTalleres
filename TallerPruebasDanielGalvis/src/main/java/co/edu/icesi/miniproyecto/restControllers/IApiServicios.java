package co.edu.icesi.miniproyecto.restControllers;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiServicios {

	public TransactionBody<Iterable<Tmio1Servicio>> getServicios();
	
	public ResponseEntity<TransactionBody<Tmio1Servicio>> saveServicio(TransactionBody<Tmio1Servicio> service);
	
	public ResponseEntity<TransactionBody<Tmio1Servicio>> removeServicio(Tmio1Servicio service);
	
	public TransactionBody<Iterable<Tmio1Servicio>> getServicesFiltered(LocalDate toCompare);
	
	public ResponseEntity<TransactionBody<Tmio1Servicio>> getServicio(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin);
	
	public ResponseEntity<TransactionBody<Tmio1Servicio>> deleteServicio(@RequestBody TransactionBody<Tmio1Servicio> service);
}
