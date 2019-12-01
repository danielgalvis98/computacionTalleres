package co.edu.icesi.miniproyecto.delegate;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class ServicioDelegateImp extends GenericDelegate implements ServicioDelegate {
	
	public ServicioDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Servicio> getAllServicios() {
		TransactionBody<List<Tmio1Servicio>> transaction = new TransactionBody<>("serviciosList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Servicio>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/servicio", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
				});
		
		Iterable<Tmio1Servicio> it = response.getBody().getBody();
		return it;
	}

	@Override
	public Tmio1Servicio addServicio(Tmio1Servicio service) {
		TransactionBody<Tmio1Servicio> transaction = new TransactionBody<>("newServicio", service);
		HttpEntity<TransactionBody<Tmio1Servicio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/servicio",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
        return service;
	}

	@Override
	public Tmio1Servicio getServicio(Tmio1ServicioPK key) {
		TransactionBody<Tmio1ServicioPK> transaction = new TransactionBody<>("idServ", key);
		HttpEntity<TransactionBody<Tmio1ServicioPK>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/servicio/" + key.getIdRuta() + "/" +
		key.getCedulaConductor() + "/" + key.getIdBus() + "/" + key.getFechaInicio().toString()
		+ "/" + key.getFechaFin().toString(),HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
		});
		return response.getBody().getBody();
	}

	@Override
	public void removeServicio(Tmio1Servicio ser) {
		TransactionBody<Tmio1Servicio> transaction = new TransactionBody<>("delServ", ser);
		HttpEntity<TransactionBody<Tmio1Servicio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/servicio", HttpMethod.DELETE, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
				});
	}

	@Override
	public Iterable<Tmio1Servicio> getServiciosByDate(LocalDate date) {
		TransactionBody<List<Tmio1Servicio>> transaction = new TransactionBody<>("serviciosList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Servicio>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/servicio/" + date, HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
				});
		
		Iterable<Tmio1Servicio> it = response.getBody().getBody();
		return it;
	}

}
