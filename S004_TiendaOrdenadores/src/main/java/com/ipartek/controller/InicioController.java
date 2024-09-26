package com.ipartek.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.auxiliares.AdvancedLogger;
import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.model.Ordenador;
import com.ipartek.model.Privilegio;
import com.ipartek.repository.OrdenadorRepository;
import com.ipartek.service.OrdenadorService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class InicioController {

	@Autowired
	private OrdenadorRepository productosRepo;
	
	
	
	OrdenadorService ord= new OrdenadorService();
	
	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);
//	@RequestMapping("/")
//	public String index(@RequestParam(defaultValue = "0") int page,Model model, HttpSession session) {
//		
//		
//		int pageSize = 10; // Número de elementos por página
//	    Page<Ordenador> ordenadoresPage = ord.findPaginated(PageRequest.of(page, pageSize));
//		
//
////		model.addAttribute("atr_lista_ordenadores", productosRepo.findAll());
//
//	    model.addAttribute("atr_lista_ordenadores", ordenadoresPage.getContent());
//	    model.addAttribute("currentPage", page);
//	    model.addAttribute("totalPages", ordenadoresPage.getTotalPages());
//		
//	
//		session.setAttribute("rol", Privilegio.USUARIO);
//		session.setAttribute("Intentos", 0);
//		return "index";
//
//	}
	@GetMapping("/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
	    Page<Ordenador> page = ord.findPage(currentPage);
	    int totalPages = page.getTotalPages();
	    long totalItems = page.getTotalElements();
	    List<Ordenador> countries = page.getContent();

	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("totalItems", totalItems);
	    model.addAttribute("countries", countries);

	    return "index";
	}
	@GetMapping("/")
	public String getAllPages(Model model){
	    return getOnePage(model, 1);
	}
	
	
	
	
	
	
}
