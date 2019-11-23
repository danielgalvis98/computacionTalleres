package co.edu.icesi.miniproyecto.delegate;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@Component
public class ServicioDelegateImp implements ServicioDelegate {
	
	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/servicio";
	
	public ServicioDelegateImp() {
		restTemplate=new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1Servicio> getAllServicios() {
		Tmio1Servicio[] servicios=restTemplate.getForObject(SERVER,Tmio1Servicio[].class);
		return Arrays.asList(servicios);
	}

	@Override
	public Tmio1Servicio addServicio(Tmio1Servicio service) {
		return restTemplate.postForEntity(SERVER+"/add",service,Tmio1Servicio.class).getBody();
	}

	@Override
	public Tmio1Servicio getServicio(Tmio1ServicioPK key) {
		return restTemplate.getForObject(SERVER+"/"+key.getIdRuta()+"/"+key.getCedulaConductor()+
				"/"+key.getIdBus()+"/"+key.getFechaInicio()+"/"+key.getFechaFin(),Tmio1Servicio.class);
	}

	@Override
	public void removeServicio(Tmio1Servicio ser) {
		Tmio1ServicioPK key=ser.getId();
		restTemplate.delete(SERVER+"/"+key.getIdRuta()+"/"+key.getCedulaConductor()+
				"/"+key.getIdBus()+"/"+key.getFechaInicio()+"/"+key.getFechaFin(),Tmio1Servicio.class);
	}

}
