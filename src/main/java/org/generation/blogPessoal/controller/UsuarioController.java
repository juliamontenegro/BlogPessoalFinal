package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	
	
	@GetMapping("/all")

	public ResponseEntity<List<Usuario>> getAll() {

		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{id}")

	public ResponseEntity<Usuario> getById(@PathVariable long id) {

		return repository.findById(id)

				.map(resp -> ResponseEntity.ok(resp))

				.orElse(ResponseEntity.notFound().build());

	}

	
	
	@PutMapping("/atualizar")

	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){		

	        return service.atualizarUsuario(usuario)

	                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))

	                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}
	
	
	
	
	@PostMapping("/logar")
	public ResponseEntity<Postagem> Autentication(@RequestBody Optional<UserLogin> user) {

		return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> Post(@RequestBody Usuario usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(usuario));
	}
	
	
	
	
	
	
}
