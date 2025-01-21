package com.generation.fitness.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.generation.fitness.model.Usuario;
import com.generation.fitness.repository.UsuarioRepository;

@Service
public class IMCService {
	
		@Autowired
	    private UsuarioRepository usuarioRepository;

	    // Método para calcular o IMC
	    public ResponseEntity<String> calcularIMC(Long id) {
	        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

	        if (usuarioOptional.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        Usuario usuario = usuarioOptional.get();
	        double peso = usuario.getPeso();
	        double altura = usuario.getAltura();

	        if (peso <= 0 || altura <= 0) {
	            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
	        }

	        double imc = peso / (altura * altura);
	        String categoria = categorizarIMC(imc);

	        return ResponseEntity.ok("IMC: " + imc + " - Categoria: " + categoria);
	    }

	    // Categorizar o IMC
	    private String categorizarIMC(double imc) {
	        if (imc < 18.5) {
	            return "Abaixo do peso";
	        } else if (imc >= 18.5 && imc < 24.9) {
	            return "Peso normal";
	        } else if (imc >= 25.0 && imc < 29.9) {
	            return "Sobrepeso";
	        } else {
	            return "Obesidade";
	        }
	    }
		
	}

