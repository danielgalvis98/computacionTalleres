package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Component
public class RutaDelegateImp extends GenericDelegate implements RutaDelegate {
	
	public RutaDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Ruta> getAllRutas() {
		Tmio1Ruta[] rutas=restTemplate.getForObject(SERVER+"api/ruta",Tmio1Ruta[].class);
		return Arrays.asList(rutas);
	}

	@Override
	public Tmio1Ruta addTmio1Ruta(Tmio1Ruta ruta) {
		return restTemplate.postForEntity(SERVER+"api/ruta",ruta,Tmio1Ruta.class).getBody();
	}

	@Override
	public Tmio1Ruta getRuta(Integer idRuta) {
		return restTemplate.getForObject(SERVER+"api/ruta/"+idRuta,Tmio1Ruta.class);
	}

}
