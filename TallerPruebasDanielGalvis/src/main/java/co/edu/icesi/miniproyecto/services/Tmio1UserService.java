package co.edu.icesi.miniproyecto.services;

import java.util.Optional;

import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.model.UserType;

public interface Tmio1UserService {
	public void save (Tmio1Usuario user);
	
	public Optional<Tmio1Usuario> findById(long id);
	
	public Iterable<Tmio1Usuario> findAll();

	public Iterable<Tmio1Usuario> findAllAdmins();

	public Iterable<Tmio1Usuario> findAllOperators();
	
	public void delete(Tmio1Usuario user);
	
	public UserType [] getTypes();

}
