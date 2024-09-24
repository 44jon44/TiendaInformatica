package com.ipartek.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="modelos")

public class Modelo {
	
	@Id
	@Column(name="id")
	private int id;
		
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany
	private List<Ordenador> listaOrdenadores;

	public Modelo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Modelo() {
		super();
		this.id = 0;
		this.nombre = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toCSV() {
	    return id + "," + nombre;
	}
	
}
