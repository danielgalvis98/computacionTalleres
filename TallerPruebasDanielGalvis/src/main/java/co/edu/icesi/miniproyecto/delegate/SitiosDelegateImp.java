package co.edu.icesi.miniproyecto.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@Component
public class SitiosDelegateImp extends GenericDelegate implements SitiosDelegate {
	
	public SitiosDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<Tmio1Sitio> getAllSitios() {
		TransactionBody<List<Tmio1Sitio>> transaction = new TransactionBody<>("sitioList", new ArrayList<>());
		HttpEntity<TransactionBody<List<Tmio1Sitio>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<Tmio1Sitio>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/sitios", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<Tmio1Sitio>>>() {
				});
		
		Iterable<Tmio1Sitio> it = response.getBody().getBody();
		return it;
	}

	@Override
	public void addTmio1Sitio(Tmio1Sitio tmio1Sitio) {
		TransactionBody<Tmio1Sitio> transaction = new TransactionBody<>("newSitio",tmio1Sitio);
		HttpEntity<TransactionBody<Tmio1Sitio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/sitios",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
		});
	}

	@Override
	public Tmio1Sitio getSitioById(long idSitio) {
		TransactionBody<Long> transaction = new TransactionBody<>("sitioid", idSitio);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/sitios/" + idSitio,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
		});
		return response.getBody().getBody();
	}

	@Override
	public void removeSitio(Tmio1Sitio tmio1Sitio) throws Exception {
		TransactionBody<Tmio1Sitio> transaction = new TransactionBody<>("delSitio",tmio1Sitio);
		HttpEntity<TransactionBody<Tmio1Sitio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/sitios", HttpMethod.DELETE, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				});
		if(response.getStatusCode().equals(HttpStatus.PRECONDITION_FAILED)) {
			Exception e =new Exception("Sitio referenciado por sitiosRuta");
			throw e;
		}
	}

	@Override
	public void updateTmio1Sitio(Tmio1Sitio tmio1Sitio) {
		TransactionBody<Tmio1Sitio> transaction = new TransactionBody<>("uptSitio",tmio1Sitio);
		HttpEntity<TransactionBody<Tmio1Sitio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		response = restTemplate.exchange(SERVER+"api/sitios", HttpMethod.PUT, request,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				});
	}

}
