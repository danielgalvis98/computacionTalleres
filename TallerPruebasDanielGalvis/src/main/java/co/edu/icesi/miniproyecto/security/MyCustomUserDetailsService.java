/*package co.edu.icesi.miniproyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.repository.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepository repository) {
		userRepository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tmio1Usuario userApp = userRepository.findByUsername(username).get(0);
		if (userApp != null) {
			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword())
					.roles(userApp.getType().toString());
			return builder.build();
		}
		throw new UsernameNotFoundException("User not found");
	}

}*/
