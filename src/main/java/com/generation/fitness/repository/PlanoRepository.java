package com.generation.fitness.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness.model.Plano;



public interface PlanoRepository extends JpaRepository<Plano, Long>{

	public List<Plano> findAllByNomePlanoContainingIgnoreCase(@Param("nome") String nomePlano);
}
