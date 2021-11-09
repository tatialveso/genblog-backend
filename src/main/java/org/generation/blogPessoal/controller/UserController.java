package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.repository.UserRepository;
import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin> login) {
		return userService.Login(login).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> post(@RequestBody User username) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.RegisterUser(username));
	}
	
	@PutMapping
	public ResponseEntity<User> put(@RequestBody User username) {
		return ResponseEntity.ok(repository.save(username));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
