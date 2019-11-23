package co.edu.icesi.miniproyecto.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Component
public class RutaDelegateImp implements RutaDelegate {
	
	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/ruta";
	
	public RutaDelegateImp() {
		restTemplate=new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1Ruta> getAllRutas() {
		Tmio1Ruta[] rutas=restTemplate.getForObject(SERVER,Tmio1Ruta[].class);
		return Arrays.asList(rutas);
	}

	@Override
	public Tmio1Ruta addTmio1Ruta(Tmio1Ruta ruta) {
		System.out.println(ruta.getDiaFin());
		System.out.println(ruta.getDiaInicio());
		return restTemplate.postForEntity(SERVER+"/add",ruta,Tmio1Ruta.class).getBody();
	}

	@Override
	public Tmio1Ruta getRuta(Integer idRuta) {
		return restTemplate.getForObject(SERVER+"/"+idRuta,Tmio1Ruta.class);
	}

}
