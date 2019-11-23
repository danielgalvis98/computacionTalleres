package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Component
public class BusDelegateImp extends GenericDelegate implements BusDelegate  {
	
	public BusDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Bus> getAllBuses() {
		Tmio1Bus[] response = restTemplate.getForObject(SERVER+"api/buses",Tmio1Bus[].class);
		return Arrays.asList(response);
	}

	@Override
	public Tmio1Bus addTmio1Bus(Tmio1Bus bus) {
        ResponseEntity<Tmio1Bus> re=restTemplate.postForEntity(SERVER+"api/buses",bus,Tmio1Bus.class);
		Tmio1Bus bas=re.getBody();
		return bas;
	}

	@Override
	public Tmio1Bus getBus(Integer idBus) {
		return restTemplate.getForObject(SERVER+"api/buses/"+idBus,Tmio1Bus.class);
	}

}
