package co.edu.icesi.miniproyecto.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class ConductorDelegateImp extends GenericDelegate implements ConductorDelegate {
	
	public ConductorDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Conductore> getAllConductores() {
		TransactionBody<List<Tmio1Conductore>> transaction = new TransactionBody<>("conducsList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Conductore>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Conductore>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/conductores", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Conductore>>>() {
				});
		
		Iterable<Tmio1Conductore> it = response.getBody().getBody();
		return it;
	}

	@Override
	public Tmio1Conductore addConductor(Tmio1Conductore conductor) {
		TransactionBody<Tmio1Conductore> transaction = new TransactionBody<>("newConduc", conductor);
		HttpEntity<TransactionBody<Tmio1Conductore>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/conductores",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
		return conductor;
	}

	@Override
	public Tmio1Conductore getConductor(String cedulaConductor) {
		TransactionBody<String> transaction = new TransactionBody<>("cedulaConduc", cedulaConductor);
		HttpEntity<TransactionBody<String>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/conductores/" + cedulaConductor,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
		});
		return response.getBody().getBody();
	}

}
