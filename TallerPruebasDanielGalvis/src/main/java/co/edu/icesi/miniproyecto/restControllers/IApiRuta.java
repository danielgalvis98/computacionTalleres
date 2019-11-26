package co.edu.icesi.miniproyecto.restControllers;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface IApiRuta {
	public Iterable<Tmio1Ruta> getRutas();
	
	public void addTmio1Ruta(Tmio1Ruta ruta) throws Exception;
	
	public Tmio1Ruta getRuta(Integer id);

}
