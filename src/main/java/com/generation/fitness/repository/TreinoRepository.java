package com.generation.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long>{
	
	
	public List<Treino> findAllByExercicioContainingIgnoreCase(@Param("exercicio") String exercicio);

}
