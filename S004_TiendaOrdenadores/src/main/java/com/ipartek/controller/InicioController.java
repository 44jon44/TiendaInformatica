package com.ipartek.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.auxiliares.AdvancedLogger;
import com.ipartek.model.Privilegio;
import com.ipartek.repository.OrdenadorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {

	@Autowired
	private OrdenadorRepository productosRepo;

	
	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		
		model.addAttribute("atr_lista_ordenadores", productosRepo.findAll());

		session.setAttribute("rol", Privilegio.USUARIO);
		session.setAttribute("Intentos", 0);
		return "index";

	}
}
