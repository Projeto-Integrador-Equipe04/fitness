package com.generation.fitness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_treino")
public class Treino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 4, max = 50, message = "Digite no minimo 4 no maximo 50 caracteres")
	private String exercicio;
	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 1, max = 5, message = "Digite no minimo 1 no maximo 5 caracteres")
	private Integer num_serie;
	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 1, max = 5, message = "Digite no minimo 1 no maximo 5 caracteres")
	private String carga;
	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 3, max = 20, message = "Digite no minimo 3 no maximo 20 caracteres")
	private String descanso;
	
	//Getters e Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExercicio() {
		return exercicio;
	}

	public void setExercicio(String exercicio) {
		this.exercicio = exercicio;
	}

	public Integer getNum_serie() {
		return num_serie;
	}

	public void setNum_serie(Integer num_serie) {
		this.num_serie = num_serie;
	}

	public String getCarga() {
		return carga;
	}

	public void setCarga(String carga) {
		this.carga = carga;
	}

	public String getDescanso() {
		return descanso;
	}

	public void setDescanso(String descanso) {
		this.descanso = descanso;
	}
	
	
	
}
