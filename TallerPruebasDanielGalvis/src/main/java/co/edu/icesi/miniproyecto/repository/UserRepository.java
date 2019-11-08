package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.model.UserType;


public interface UserRepository extends CrudRepository<Tmio1Usuario, Long>{
	List<Tmio1Usuario> findByUsername(String username);
	
	List<Tmio1Usuario> findByType(UserType type);

}
