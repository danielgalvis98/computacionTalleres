package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.TransactionBody;

public interface IApiSitioRuta {
	
	public TransactionBody<Iterable<Tmio1SitiosRuta>> getSitioRutas();
	
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> saveSitioRuta (@RequestBody TransactionBody<Tmio1SitiosRuta> sitioRuta);
	
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> getSitioRuta (@RequestParam int idSitio, @RequestParam int idRuta);
	
	public ResponseEntity<TransactionBody<Tmio1SitiosRuta>> deleteSitioRuta (@RequestBody TransactionBody<Tmio1SitiosRuta> sitioRuta);

}
