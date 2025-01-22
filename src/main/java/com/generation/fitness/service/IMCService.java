package com.generation.fitness.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	        

	        double imc = peso / (altura * altura);
	        String categoria = categorizarIMC(imc);

	        BigDecimal imc2 = new BigDecimal(imc);
	        imc2 = imc2.setScale(2, RoundingMode.HALF_UP);
	        
	        return ResponseEntity.ok("IMC: " + imc2 + " - Categoria: " + categoria);
	    }

	    // Categorizar o IMC
	    private String categorizarIMC(double imc2) {
	        if (imc2 < 18.5)
	        return "Abaixo do peso";
	        
	        if (imc2 < 24.9) 
	        return "Peso normal";
	        
	        if (imc2 < 29.9) 
	        return "Sobrepeso";

	            return "Obesidade";
	        }
	    }
		
	

