package com.generation.fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public List <Usuario> findAllByNomeContainingIgnoreCase
	(@Param("nome") String nome);

}
