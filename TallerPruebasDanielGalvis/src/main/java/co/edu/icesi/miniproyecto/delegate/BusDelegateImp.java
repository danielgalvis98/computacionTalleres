package co.edu.icesi.miniproyecto.delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class BusDelegateImp extends GenericDelegate implements BusDelegate  {
	
	public BusDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Bus> getAllBuses() {
		TransactionBody<List<Tmio1Bus>> transaction = new TransactionBody<>("carList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Bus>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/buses", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
				});
		
		Iterable<Tmio1Bus> it = response.getBody().getBody();
		return it;
	}

	@Override
	public Tmio1Bus addTmio1Bus(Tmio1Bus bus) {
		TransactionBody<Tmio1Bus> transaction = new TransactionBody<>("newBus", bus);
		HttpEntity<TransactionBody<Tmio1Bus>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/buses",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
		});
        
		return bus;
	}

	@Override
	public Tmio1Bus getBus(Integer idBus) {
		TransactionBody<Integer> transaction = new TransactionBody<>("busid", idBus);
		HttpEntity<TransactionBody<Integer>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/buses/" + idBus,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
		});
		return response.getBody().getBody();
	}

}
