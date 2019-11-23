package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Component
public class BusDelegateImp implements BusDelegate {
	
	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/";
	
	public BusDelegateImp() {
		restTemplate=new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1Bus> getAllBuses() {
		Tmio1Bus[] response = restTemplate.getForObject("http://localhost:8080/api/buses",Tmio1Bus[].class);
		return Arrays.asList(response);
	}

	@Override
	public Tmio1Bus addTmio1Bus(Tmio1Bus bus) {
		System.out.println("DELEGATE");		
        ResponseEntity<Tmio1Bus> re=restTemplate.postForEntity("http://localhost:8080/api/buses",bus,Tmio1Bus.class);
		System.out.println(re.getStatusCodeValue());
		System.out.println(re.getHeaders().toString());
		Tmio1Bus bas=re.getBody();
		System.out.println(bas);
		return bas;
	}

	@Override
	public Tmio1Bus getBus(Integer idBus) {
		return restTemplate.getForObject(SERVER+"api/buses/"+idBus,Tmio1Bus.class);
	}

}
