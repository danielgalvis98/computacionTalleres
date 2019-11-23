package co.edu.icesi.miniproyecto.delegate;

import javax.validation.Valid;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ConductorDelegate {

	public Iterable<Tmio1Conductore> getAllConductores();

	public Tmio1Conductore addConductor(Tmio1Conductore conductor);

	public Tmio1Conductore getConductor(String cedulaConductor);

}
