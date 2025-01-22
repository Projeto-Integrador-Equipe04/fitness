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

import com.generation.fitness.model.Plano;
import com.generation.fitness.repository.PlanoRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/planos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanoController {

	@Autowired
	private PlanoRepository planoRepository;
	
	//lista todos os tipos
	@GetMapping
	public ResponseEntity<List<Plano>> getAll(){
		
		return ResponseEntity.ok(planoRepository.findAll());
	}
	
	//Buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Plano> getById(@PathVariable Long id){
		return planoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//buscar por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Plano>> buscarPorNome(@PathVariable String nome) {
	    return ResponseEntity.ok(planoRepository.findAllByNomePlanoContainingIgnoreCase(nome));
	
	}
	
	//Criar um novo tipo
	@PostMapping
	public ResponseEntity<Plano> post (@Valid @RequestBody Plano plano){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(planoRepository.save(plano));
		
	}
	//atualizar
	 @PutMapping
	    public ResponseEntity<Plano> put(@Valid @RequestBody Plano plano){
	        return planoRepository.findById(plano.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(planoRepository.save(plano)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	  }
	  
	  //Deletar um tipo por ID
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  @DeleteMapping("/{id}")
	   public void delete(@PathVariable Long id) {
	        Optional<Plano> plano = planoRepository.findById(id);
	        if(plano.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        planoRepository.deleteById(id);        
	                
	    }
	  
	  
}
