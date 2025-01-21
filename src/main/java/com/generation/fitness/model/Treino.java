package com.generation.fitness.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "tb_treinos")
public class Treino{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 4, max = 50, message = "Digite no minimo 4 no maximo 50 caracteres")
	private String exercicio;
	
	
	@NotNull(message = "Esse campo é obrigatório")
	@Min (1) 
	@Max (50)
	private Integer num_serie;
	

	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 1, max = 100, message = "Digite no minimo 1 no maximo 100 caracteres")
	private String carga;

	
	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 3, max = 20, message = "Digite no minimo 3 no maximo 20 caracteres")
	private String descanso;
	
	@ManyToOne
	@JsonIgnoreProperties("treinos")
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
