package co.edu.icesi.miniproyecto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.model.UserType;
import co.edu.icesi.miniproyecto.repository.UserRepository;

@Service
public class Tmio1UserServiceImpl implements Tmio1UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public Tmio1UserServiceImpl(UserRepository userRepo) {
		userRepository = userRepo;
	}

	@Override
	public void save(Tmio1Usuario user) {
		userRepository.save(user);
		
	}

	@Override
	public Optional<Tmio1Usuario> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public Iterable<Tmio1Usuario> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<Tmio1Usuario> findAllAdmins() {
		return userRepository.findByType(UserType.admin);
	}

	@Override
	public Iterable<Tmio1Usuario> findAllOperators() {
		return userRepository.findByType(UserType.operador);
	}

	@Override
	public void delete(Tmio1Usuario user) {
		userRepository.delete(user);
		
	}

	@Override
	public UserType[] getTypes() {
		return UserType.values();
	}

}
