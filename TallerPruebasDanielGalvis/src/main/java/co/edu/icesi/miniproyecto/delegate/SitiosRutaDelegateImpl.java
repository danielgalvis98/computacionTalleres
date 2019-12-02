package co.edu.icesi.miniproyecto.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class SitiosRutaDelegateImpl extends GenericDelegate implements SitiosRutaDelegate {

	public SitiosRutaDelegateImpl() {
		super();
	}
	
	@Override
	public Iterable<Tmio1SitiosRuta> getAllSitiosRuta() {
		TransactionBody<List<Tmio1SitiosRuta>> transaction = new TransactionBody<>("sitiosRutasList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1SitiosRuta>>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<List<Tmio1SitiosRuta>>> response = null;
		response = restTemplate.exchange(SERVER + "/api/sitioruta", HttpMethod.GET,
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1SitiosRuta>>>() {
				});
		return response.getBody().getBody();
	}

	@Override
	public Tmio1SitiosRuta addSitioRuta(Tmio1SitiosRuta sitioRuta) {
		TransactionBody<Tmio1SitiosRuta> transaction = new TransactionBody<>("newSitioRuta", sitioRuta);
		HttpEntity<TransactionBody<Tmio1SitiosRuta>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = null;
		response = restTemplate.exchange(SERVER + "/api/sitioruta", HttpMethod.POST,
				request, new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
				});
		if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
		
		return sitioRuta;
		
	}

	@Override
	public Tmio1SitiosRuta getSitioRuta(Tmio1SitiosRutaPK key) {
		TransactionBody<Tmio1SitiosRutaPK> transaction = new TransactionBody<>("key", key);
		HttpEntity<TransactionBody<Tmio1SitiosRutaPK>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/sitioruta/" + key.getIdSitio()
		+ "/" + key.getIdRuta() ,HttpMethod.GET, request,
		        		new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
				});
		
		return response.getBody().getBody();
	}

	@Override
	public void removeSitioRuta(Tmio1SitiosRuta oldSitioRuta) {
		TransactionBody<Tmio1SitiosRuta> transaction = new TransactionBody<>("delServ", oldSitioRuta);
		HttpEntity<TransactionBody<Tmio1SitiosRuta>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/sitioruta", HttpMethod.DELETE, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
				});

	}

}
