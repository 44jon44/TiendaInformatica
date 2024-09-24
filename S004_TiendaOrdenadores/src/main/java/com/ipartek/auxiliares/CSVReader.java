package com.ipartek.auxiliares;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.model.Ordenador;


public class CSVReader {

	public static List<Object> leerCSV(String archivo, String tipo) {
		
		List<Object> ordenadores = new ArrayList<>();


		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;

			reader.readLine();//Ignora Cabecera
			if (tipo == Ordenador.class.getName()) {

				while ((linea = reader.readLine()) != null) {
					String[] valores = linea.split(",");

					Ordenador ordenador = new Ordenador();
					
					ordenador.setId(Integer.parseInt(valores[0]));
					ordenador.setNumeroSerie(valores[1]);
					ordenador.setMarca(new Marca(0, valores[1]));
					ordenador.setModelo(new Modelo(0, valores[1]));
					ordenador.setAnotaciones(valores[3]);
					ordenador.setCapacidad(valores[3]);
					ordenador.setRam(valores[3]);
					
					
					
					ordenadores.add(ordenador);
				}
				return ordenadores;
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}
}
