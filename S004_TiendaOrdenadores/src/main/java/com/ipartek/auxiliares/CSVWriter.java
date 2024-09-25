package com.ipartek.auxiliares;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.model.Ordenador;


import jakarta.servlet.http.HttpSession;

public class CSVWriter {

    // Método para escribir la lista de objetos en un archivo CSV
    public static void escribirCSV(String archivo, List<Object> objetos, HttpSession session) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {

            String modificacion = (String) session.getAttribute("modificacion");

            switch (modificacion) {
                case "copiaSeguridadOrdenadores":
                    // Cabecera del csv para productos
                    writer.write("id,numeroSerie,marca,modelo,anotaciones,capacidad,ram" + "\n");
                    for (Object prod : objetos) {
                        if (prod instanceof Ordenador) {
                            Ordenador ord = (Ordenador) prod;
                            writer.write(ord.toCSV() + System.lineSeparator());
                        }
                    }
                    break;

                case "copiaSeguridadMarcas":
                    // Cabecera del csv para categorías
                    writer.write("id,nombre" + "\n");
                    for (Object cat : objetos) {
                        if (cat instanceof Marca) {
                        	Marca marca = (Marca) cat;
                            writer.write(marca.toCSV() + System.lineSeparator());
                        }
                    }
                    break;

                case "copiaSeguridadModelos":
                    // Cabecera del csv para géneros
                    writer.write("id,genero" + "\n");
                    for (Object gen : objetos) {
                        if (gen instanceof Modelo) {
                        	Modelo modelo = (Modelo) gen;
                            writer.write(modelo.toCSV() + System.lineSeparator());
                        }
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Tipo de modificación no soportada: " + modificacion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
