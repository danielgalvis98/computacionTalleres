package co.edu.icesi.miniproyecto.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tmio1Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	private UserType type;

	public void setUsername(String string) {
		username=string;
	}

	public void setPassword(String encode) {
		password=encode;
	}

	public void setType(UserType operador) {
		type=operador;
	}

	public UserType getType() {
		return type;
	}

	public String getPassword() {
		return password;
	}
	
}
