package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
	@Autowired
	private PostRepository repository;
}
