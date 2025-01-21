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

import com.generation.fitness.model.Treino;
import com.generation.fitness.repository.TreinoRepository;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/treinos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TreinoController {
	
	@Autowired
	private TreinoRepository treinoRepository;
	
	@GetMapping 
	public ResponseEntity<List<Treino>> getAll(){
		return ResponseEntity.ok(treinoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Treino> getById(@PathVariable Long id){
		return treinoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/exercicio/{exercicio}")
	public ResponseEntity<List<Treino>> getByExercicio(@PathVariable String exercicio){
		return ResponseEntity.ok(treinoRepository.findAllByExercicioContainingIgnoreCase(exercicio));
	}
	
    @PostMapping
    public ResponseEntity<Treino> post(@Valid @RequestBody Treino treino){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(treinoRepository.save(treino));
    }
	

    @PutMapping
    public ResponseEntity<Treino> put(@Valid @RequestBody Treino treino){
    	return treinoRepository.findById(treino.getId())
    			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
    			.body(treinoRepository.save(treino)))
    			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Treino> treino = treinoRepository.findById(id);
        
        if(treino.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        treinoRepository.deleteById(id);              
    }
    
}
