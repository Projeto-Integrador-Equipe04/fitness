package com.generation.fitness.controller;

import java.util.List;
import java.util.Optional;

import com.generation.fitness.dto.UsuarioCadastroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.generation.fitness.model.Usuario;
import com.generation.fitness.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody UsuarioCadastroDto usuarioCadastroDto) {
		Optional<Usuario> usuarioOpt = Optional.ofNullable(usuarioRepository.findByUsuario(usuarioCadastroDto.usuario()));
		if(usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
		public ResponseEntity<Usuario> getById(@PathVariable Long id){
			return usuarioRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> getByname(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario){
		return usuarioRepository.findById(usuario.getId()).map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioRepository.save(usuario))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		usuarioRepository.deleteById(id);
	}

}
