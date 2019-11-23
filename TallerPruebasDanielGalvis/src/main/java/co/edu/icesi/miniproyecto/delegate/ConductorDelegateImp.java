package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Component
public class ConductorDelegateImp extends GenericDelegate implements ConductorDelegate {
	
	public ConductorDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Conductore> getAllConductores() {
		Tmio1Conductore[] conductores=restTemplate.getForObject(SERVER+"api/conductores",Tmio1Conductore[].class);
		return Arrays.asList(conductores);
	}

	@Override
	public Tmio1Conductore addConductor(Tmio1Conductore conductor) {
		return restTemplate.postForEntity(SERVER+"api/conductores",conductor,Tmio1Conductore.class).getBody();
	}

	@Override
	public Tmio1Conductore getConductor(String cedulaConductor) {
		return restTemplate.getForObject(SERVER+"api/conductores/"+cedulaConductor,Tmio1Conductore.class);
	}

}
