package co.edu.icesi.miniproyecto.delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class RutaDelegateImp extends GenericDelegate implements RutaDelegate {
	
	public RutaDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Ruta> getAllRutas() {
		TransactionBody<List<Tmio1Ruta>> transaction = new TransactionBody<>("rutasList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Ruta>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/ruta", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
				});
		
		Iterable<Tmio1Ruta> it = response.getBody().getBody();
		return it;
	}

	@Override
	public Tmio1Ruta addTmio1Ruta(Tmio1Ruta ruta) {
		TransactionBody<Tmio1Ruta> transaction = new TransactionBody<>("newRuta", ruta);
		HttpEntity<TransactionBody<Tmio1Ruta>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/ruta",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
        return ruta;
	}

	@Override
	public Tmio1Ruta getRuta(Integer idRuta) {
		TransactionBody<Integer> transaction = new TransactionBody<>("idRuta", idRuta);
		HttpEntity<TransactionBody<Integer>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/ruta/" + idRuta,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
		});
		return response.getBody().getBody();
	}

}
