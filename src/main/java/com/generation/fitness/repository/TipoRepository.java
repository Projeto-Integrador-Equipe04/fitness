package com.generation.fitness.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness.model.Tipo;


public interface TipoRepository extends JpaRepository<Tipo, Long>  {
	Optional<Tipo> findByNome(String nome);
	
	public List<Tipo> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
