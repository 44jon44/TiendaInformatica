package com.ipartek.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre",unique = true)
	private String nombre;
	
	@Column(name = "contraseña")
	private String contraseña;
	
	@Enumerated(EnumType.STRING)  // O EnumType.ORDINAL
    private Privilegio priv;



	public Usuario(int id, String nombre, String contraseña, Privilegio priv) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.priv = priv;
	}
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.contraseña = "";
		this.priv = null;
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
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Privilegio getPriv() {
		return priv;
	}
	public void setPriv(Privilegio priv) {
		this.priv = priv;
	}

}