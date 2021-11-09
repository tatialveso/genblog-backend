package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
	public Optional<Users> findByLogin(String login);
}
