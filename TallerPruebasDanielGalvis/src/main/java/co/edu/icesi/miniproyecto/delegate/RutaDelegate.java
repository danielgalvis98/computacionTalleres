package co.edu.icesi.miniproyecto.delegate;

import javax.validation.Valid;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface RutaDelegate {

	public Iterable<Tmio1Ruta> getAllRutas();

	public Tmio1Ruta getRuta(Integer idRuta);

	Tmio1Ruta addTmio1Ruta(Tmio1Ruta ruta);

}
