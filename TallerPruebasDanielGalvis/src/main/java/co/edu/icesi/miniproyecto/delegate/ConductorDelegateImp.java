package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Component
public class ConductorDelegateImp implements ConductorDelegate {
	
	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/conductores";
	
	public ConductorDelegateImp() {
		restTemplate=new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1Conductore> getAllConductores() {
		Tmio1Conductore[] conductores=restTemplate.getForObject(SERVER,Tmio1Conductore[].class);
		return Arrays.asList(conductores);
	}

	@Override
	public Tmio1Conductore addConductor(Tmio1Conductore conductor) {
		return restTemplate.postForEntity(SERVER+"/add",conductor,Tmio1Conductore.class).getBody();
	}

	@Override
	public Tmio1Conductore getConductor(String cedulaConductor) {
		return restTemplate.getForObject(SERVER+"/"+cedulaConductor,Tmio1Conductore.class);
	}

}
