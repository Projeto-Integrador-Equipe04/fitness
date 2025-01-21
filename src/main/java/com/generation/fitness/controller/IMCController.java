package com.generation.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.generation.fitness.service.IMCService;

@RestController
public class IMCController {

	
	@Autowired
    private IMCService imcService;

    @GetMapping("/usuarios/{id}/imc")
    public ResponseEntity<String> calcularIMC(@PathVariable Long id) {
        return imcService.calcularIMC(id);
    }
}
