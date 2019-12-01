package co.edu.icesi.miniproyecto.restControllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.TransactionBody;
import co.edu.icesi.miniproyecto.services.BusesServicio;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;
import co.edu.icesi.miniproyecto.services.RutasServicio;
import co.edu.icesi.miniproyecto.services.ServiciosServicio;
import co.edu.icesi.miniproyecto.validation.ValidateDate;

@RequestMapping("/api/servicio")
@RestController
public class ApiServiciosController implements IApiServicios{
	
	ServiciosServicio serviciosServicio;
	BusesServicio busesServicio;
	ConductoresServicio conductoresServicio;
	RutasServicio rutasServicio;
	
	@Autowired
	public ApiServiciosController(ServiciosServicio serviciosServicio, BusesServicio busesServicio,
			ConductoresServicio conductoresServicio, RutasServicio rutasServicio) {
		this.serviciosServicio = serviciosServicio;
		this.busesServicio = busesServicio;
		this.conductoresServicio = conductoresServicio;
		this.rutasServicio = rutasServicio;
	}
	
	@GetMapping("")
	public TransactionBody<Iterable<Tmio1Servicio>> getServicios(){
		TransactionBody<Iterable<Tmio1Servicio>> tb = new TransactionBody<>();
		tb.setBody(serviciosServicio.getAllServicios());
		return tb;
	}
	
	@PostMapping("")
	public ResponseEntity<TransactionBody<Tmio1Servicio>> saveServicio(@RequestBody TransactionBody<Tmio1Servicio> service) {
		Tmio1Servicio servicio = service.getBody();
		TransactionBody<Tmio1Servicio> tb;
		try {
			serviciosServicio.addServicio(servicio);
			tb  = new TransactionBody<>("NewServicio", servicio);
			ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
			
		} catch (RuntimeException e) {
			tb = new TransactionBody<>(e.getMessage(), servicio);
			ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<> (tb,
					HttpStatus.MULTI_STATUS);
			return response;
		}
	}
	
	@PostMapping("/remove")
	public ResponseEntity<TransactionBody<Tmio1Servicio>> removeServicio(Tmio1Servicio service) {
		return null;
	}
	
	@GetMapping("/{date}")
	public TransactionBody<Iterable<Tmio1Servicio>> getServicesFiltered(@PathVariable("date") LocalDate toCompare){
		List<Tmio1Servicio> toReturn = new ArrayList<Tmio1Servicio>();
		for (Tmio1Servicio serv: serviciosServicio.getAllServicios()) {
			if (serv.getId().getFechaInicio().compareTo(toCompare) <= 0 
					&& serv.getId().getFechaFin().compareTo(toCompare) >= 0)
				toReturn.add(serv);
		}
		
		TransactionBody<Iterable<Tmio1Servicio>> tb = new TransactionBody<>();
		tb.setBody(toReturn);
		return tb;
	}
	
	@GetMapping("/{idRuta}/{cedulaConductor}/{idBus}/{fechaInicio}/{fechaFin}")
	public ResponseEntity<TransactionBody<Tmio1Servicio>> getServicio(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin) {
		LocalDate inicio = LocalDate.parse(fechaInicio);
		LocalDate fin = LocalDate.parse(fechaFin);
		
		Tmio1ServicioPK key = new Tmio1ServicioPK();
		
		key.setCedulaConductor(cedulaConductor);
		key.setFechaFin(fin);
		key.setFechaInicio(inicio);
		key.setIdBus(idBus);
		key.setIdRuta(idRuta);
		Tmio1Servicio serv = serviciosServicio.getServicio(key);
		TransactionBody<Tmio1Servicio> tb = new TransactionBody<>("ActServ", serv);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("")
	public ResponseEntity<TransactionBody<Tmio1Servicio>> deleteServicio(@RequestBody TransactionBody<Tmio1Servicio> service) {
		Tmio1Servicio serv = service.getBody();
		serviciosServicio.removeServicio(serv);
		TransactionBody<Tmio1Servicio> tb = new TransactionBody<>("DelServ", serv);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<> (tb,
				HttpStatus.ACCEPTED);
		return response;
	}
}
