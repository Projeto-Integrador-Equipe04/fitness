package com.generation.fitness.controller;

import java.util.List;
import java.util.Optional;

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

import com.generation.fitness.model.Tipo;
import com.generation.fitness.repository.TipoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;
	
	//lista todos os tipos
	@GetMapping
	public ResponseEntity<List<Tipo>> getAll(){
		
		return ResponseEntity.ok(tipoRepository.findAll());
	}
	
	//Buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Tipo> getById(@PathVariable Long id){
		return tipoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//buscar por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Tipo> buscarPorNome(@PathVariable String nome) {
	    return tipoRepository.findByNome(nome)
	            .map(tipo -> ResponseEntity.ok(tipo))
	            .orElse(ResponseEntity.notFound().build());
	
	}
	
	//Criar um novo tipo
	@PostMapping
	public ResponseEntity<Tipo> post (@Valid @RequestBody Tipo tipo){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(tipoRepository.save(tipo));
	}
	//atualizar
	 @PutMapping
	    public ResponseEntity<Tipo> put(@Valid @RequestBody Tipo tipo){
	        return tipoRepository.findById(tipo.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(tipoRepository.save(tipo)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	  }
	  
	  //Deletar um tipo por ID
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  @DeleteMapping("/{id}")
	   public void delete(@PathVariable Long id) {
	        Optional<Tipo> tema = tipoRepository.findById(id);
	        if(tema.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        tipoRepository.deleteById(id);        
	                
	    }
	  
	  
}
