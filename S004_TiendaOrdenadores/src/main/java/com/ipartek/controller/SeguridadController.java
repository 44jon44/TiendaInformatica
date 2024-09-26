package com.ipartek.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.auxiliares.AdvancedLogger;

import com.ipartek.auxiliares.CSVWriter;
import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.model.Ordenador;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.ModeloRepository;
import com.ipartek.repository.OrdenadorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SeguridadController {
	
	@Autowired
	private ModeloRepository modelosRepo;
	@Autowired
	private OrdenadorRepository ordenadoresRepo;
	@Autowired
	private MarcaRepository marcasRepo;


	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);


	@RequestMapping("/copiaSeguridadModelos")
	public String copiaSeguriadProductos(Model model, @ModelAttribute(value = "obj_modelo") Modelo modelo,
			HttpSession session) {
		logger.info("Entra2");

		model.addAttribute("obj_modelo", new Modelo());

		List<Object> listaModelo = new ArrayList<>(modelosRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadModelos");

		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/modelos.csv", listaModelo, session);

		return "redirect:/admin";
	}

	@RequestMapping("/copiaSeguridadMarcas")
	public String copiaSeguriadCategorias(Model model, @ModelAttribute(value = "obj_marca") Marca marca,
			HttpSession session) {
		logger.info("Entra1");
		model.addAttribute("obj_marca", new Marca());

		List<Object> listaMarca = new ArrayList<>(marcasRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadMarcas");
		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/marcas.csv", listaMarca, session);

		return "redirect:/admin";
	}

	@RequestMapping("/copiaSeguridadOrdenadores")
	public String copiaSeguriadGeneros(Model model, @ModelAttribute(value = "obj_ordenador") Ordenador ordenador,

			HttpSession session) {
		logger.info("Entra copia PC");
		model.addAttribute("obj_ordenador", new Ordenador());

		List<Object> listaOrd = new ArrayList<>(ordenadoresRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadOrdenadores");
		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/ordenadores.csv", listaOrd, session);

		return "redirect:/admin";
	}
}