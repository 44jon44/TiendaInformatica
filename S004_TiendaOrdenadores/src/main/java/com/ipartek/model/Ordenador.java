package com.ipartek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenadores")
public class Ordenador {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "numeroSerie")
    private String numeroSerie;
    
    @Column(name = "anotaciones")
    private String anotaciones;

    @Column(name = "capacidad")
    private String capacidad;

    @Column(name = "ram")
    private String ram;
    
    @ManyToOne
    @JoinColumn(name = "marca", nullable = false)
    private Marca marca;
    
    @ManyToOne
    @JoinColumn(name = "modelo", nullable = false)
    private Modelo modelo;

	public Ordenador(int id, String numeroSerie, String anotaciones, String capacidad, String ram, Marca marca,
			Modelo modelo) {
		super();
		this.id = id;
		this.numeroSerie = numeroSerie;
		this.anotaciones = anotaciones;
		this.capacidad = capacidad;
		this.ram = ram;
		this.marca = marca;
		this.modelo = modelo;
	}
	public Ordenador() {
		super();
		this.id = 0;
		this.numeroSerie = "";
		this.anotaciones = "";
		this.capacidad = "";
		this.ram = "";
		this.marca = new Marca();
		this.modelo = new Modelo();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getAnotaciones() {
		return anotaciones;
	}
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
    public String toCSV() {
        return id + "," + numeroSerie + "," + anotaciones + "," + capacidad + "," + ram + "," + marca.getId() + "," + modelo.getId();
    }
}