package co.edu.icesi.miniproyecto.delegate;

import java.time.LocalDate;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

public interface ServicioDelegate {

	public Iterable<Tmio1Servicio> getAllServicios();

	public Tmio1Servicio addServicio(Tmio1Servicio service);

	public Tmio1Servicio getServicio(Tmio1ServicioPK key);

	public void removeServicio(Tmio1Servicio oldService);
	
	public Iterable<Tmio1Servicio> getServiciosByDate(LocalDate date);
	
}
