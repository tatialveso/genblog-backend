package org.generation.blogPessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O campo NOME precisa ser preenchido.")
	@Size(min = 3, max = 100, message = "O NOME precisa ter no mínimo 03 letras.")
	private String name;
	
	@NotNull(message = "O campo E-MAIL precisa ser preenchido.")
	@Email(message = "O campo E-MAIL precisa ser preenchido com um e-mail validado.")
	private String login;
	
	@NotNull(message = "O campo SENHA precisa ser preenchido.")
	@Size(min = 5, message = "A SENHA precisa ter no mínimo 05 dígitos.")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
