package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User RegisterUser(User username) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordEncoder = encoder.encode(username.getPassword());
		username.setPassword(passwordEncoder);
		
		return repository.save(username);
	}
	
	public Optional<UserLogin> Login(Optional<UserLogin> login) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> username = repository.findByLogin(login.get().getLogin());
		
		if(username.isPresent()) {
			if(encoder.matches(login.get().getPassword(), username.get().getPassword())) {
				String auth = login.get().getLogin() + ":" + login.get().getPassword();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				login.get().setToken(authHeader);
				login.get().setName(username.get().getName());
				
				return login;
			}
		}
		
		return null;
	}
}
