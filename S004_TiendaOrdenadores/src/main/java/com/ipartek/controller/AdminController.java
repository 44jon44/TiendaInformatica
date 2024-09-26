package com.ipartek.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.auxiliares.AdvancedLogger;

import com.ipartek.auxiliares.CSVWriter;
import com.ipartek.model.Marca;
import com.ipartek.model.Modelo;
import com.ipartek.model.Ordenador;
import com.ipartek.model.Privilegio;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.ModeloRepository;
import com.ipartek.repository.OrdenadorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private MarcaRepository marcasRepo;
	@Autowired
	private ModeloRepository modelosRepo;
	@Autowired
	private OrdenadorRepository ordenadoresRepo;

	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);

	@RequestMapping("/admin")
	public String admin(Model model, @ModelAttribute("obj_ordenador") Ordenador ord, HttpSession session) {
		if (session.getAttribute("rol").equals(Privilegio.ADMIN)) {

			model.addAttribute("atr_lista_marcas", marcasRepo.findAll());
			model.addAttribute("atr_lista_modelos", modelosRepo.findAll());
			model.addAttribute("atr_lista_ordenadores", ordenadoresRepo.findAll());

			model.addAttribute("obj_marca", new Marca());
			model.addAttribute("obj_modelo", new Modelo());
			model.addAttribute("obj_ordenador", new Ordenador());

			return "admin";
		} else {
			return "redirect:https://www.google.es/";
		}

	}

	@RequestMapping("/adminlogOut")
	public String cerrarSesion(Model model, HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping("/adminBorrarOrdenador")
	public String borrarAdmin(Model model, int id, HttpSession session) {

		try {
			ordenadoresRepo.deleteById(id);
		} catch (NumberFormatException e) {
			System.err.println("Error con el id");
			e.printStackTrace();
		}
		session.setAttribute("modificacion", "borrarOrdenador");
		return "redirect:/admin";
	}

	@RequestMapping("/adminFrmModificarOrdenador")
	public String modificarFrmOrdAdmin(Model model, @ModelAttribute("obj_ordenador") Ordenador ordenador,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("atr_lista_ordenadores", ordenadoresRepo.findAll());
		model.addAttribute("atr_lista_marcas", marcasRepo.findAll());
		model.addAttribute("atr_lista_modelos", modelosRepo.findAll());

		if (id != null) {
			ordenador = ordenadoresRepo.findById(id).orElse(new Ordenador());
		} else {
			ordenador = new Ordenador();

		}

		model.addAttribute("obj_ordenador", ordenador);
		return "frm_modificar_ordenadores";
	}

	@RequestMapping("/adminModificarOrdenador")
	public String modificarOrdenadorAdmin(Model model, @ModelAttribute(value = "obj_ordenador") Ordenador ordenador,
			HttpSession session) {

		ordenadoresRepo.save(ordenador);

		session.setAttribute("modificacion", "modificarOrdenador");
		return "redirect:/admin";
	}

	@RequestMapping("/adminAnadirOrdenador")
	public String anadirProductoAdmin(Model model, @ModelAttribute(value = "obj_producto") Ordenador ordenador,
			HttpSession session) {

		model.addAttribute("obj_ordenador", ordenador);
		ordenadoresRepo.save(ordenador);

		session.setAttribute("modificacion", "anadirOrdenador");
		return "redirect:/admin";
	}

	@RequestMapping("/buscarPorNumeroSerie")
	public String buscarNumeroSerieAdmin(Model model, @ModelAttribute(value = "obj_ordenador") Ordenador ordenador,
			HttpSession session) {
		model.addAttribute("obj_ordenador", new Ordenador());
		model.addAttribute("obj_marca", new Marca());
		model.addAttribute("obj_modelo", new Modelo());

		List<Ordenador> listaProd = ordenadoresRepo.buscarProducto(ordenador.getNumeroSerie());
		
		model.addAttribute("atr_lista_ordenadores", listaProd);
		model.addAttribute("atr_lista_marcas", marcasRepo.findAll());
		model.addAttribute("atr_lista_modelos", modelosRepo.findAll());

		session.setAttribute("modificacion", "buscarProducto");
		return "admin";
	}
	@RequestMapping("/resetearFiltro")
	public String restearFiltroAdmin(Model model, @ModelAttribute(value = "obj_ordenador") Ordenador ordenador,
			HttpSession session) {
		return "redirect:/admin";
	}
	
	
	
	@RequestMapping("/adminBorrarMarca")
	public String borrarMarcaAdmin(Model model, int id, HttpSession session) {

		try {
			ordenadoresRepo.eliminarOrdenadoresPorMarca(id);
			marcasRepo.deleteById(id);
		} catch (NumberFormatException e) {
			System.err.println("Error con el id");
			e.printStackTrace();
		}
		session.setAttribute("modificacion", "borrarOrdenador");
		return "redirect:/admin";
	}

	@RequestMapping("/adminFrmModificarMarca")
	public String modificarFrmMarcaAdmin(Model model, @ModelAttribute("obj_marca") Marca marca,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("atr_lista_ordenadores", ordenadoresRepo.findAll());
		model.addAttribute("atr_lista_marcas", marcasRepo.findAll());
		model.addAttribute("atr_lista_modelos", modelosRepo.findAll());

		if (id != null) {
			marca = marcasRepo.findById(id).orElse(new Marca());
		} else {
			marca = new Marca();

		}

		model.addAttribute("obj_marca", marca);
		return "frm_modificar_marcas";
	}

	@RequestMapping("/adminModificarMarca")
	public String modificarMarcaAdmin(Model model, @ModelAttribute(value = "obj_marca") Marca marca,
			HttpSession session) {

		marcasRepo.save(marca);

		session.setAttribute("modificacion", "modificarMarca");
		return "redirect:/admin";
	}

	@RequestMapping("/adminAnadirMarca")
	public String anadirMarcaAdmin(Model model, @ModelAttribute(value = "obj_marca") Marca marca, HttpSession session) {

		model.addAttribute("obj_marca", marca);
		marca.setId(marcasRepo.findMaxId());
		marcasRepo.save(marca);

		session.setAttribute("modificacion", "anadirMarca");
		return "redirect:/admin";
	}

//--------------------------------------------------------------------------------
	@RequestMapping("/adminBorrarModelo")
	public String borrarModeloAdmin(Model model, int id, HttpSession session) {

		try {
			ordenadoresRepo.eliminarOrdenadoresPorModelo(id);
			modelosRepo.deleteById(id);
		} catch (NumberFormatException e) {
			System.err.println("Error con el id");
			e.printStackTrace();
		}
		session.setAttribute("modificacion", "borrarModelo");
		return "redirect:/admin";
	}

	@RequestMapping("/adminFrmModificarModelo")
	public String modificarFrmModeloAdmin(Model model, @ModelAttribute("obj_modelo") Modelo modelo,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("atr_lista_ordenadores", ordenadoresRepo.findAll());
		model.addAttribute("atr_lista_marcas", marcasRepo.findAll());
		model.addAttribute("atr_lista_modelos", modelosRepo.findAll());

		if (id != null) {
			modelo = modelosRepo.findById(id).orElse(new Modelo());
		} else {
			modelo = new Modelo();

		}

		model.addAttribute("obj_modelo", modelo);
		return "frm_modificar_modelos";
	}

	@RequestMapping("/adminModificarModelo")
	public String modificarModeloAdmin(Model model, @ModelAttribute(value = "obj_modelo") Modelo modelo,
			HttpSession session) {

		modelosRepo.save(modelo);

		session.setAttribute("modificacion", "modificarModelo");
		return "redirect:/admin";
	}

	@RequestMapping("/adminAnadirModelo")
	public String anadirModeloAdmin(Model model, @ModelAttribute(value = "obj_modelo") Modelo modelo,
			HttpSession session) {

		model.addAttribute("obj_modelo", modelo);
		modelo.setId(modelosRepo.findMaxId());
		modelosRepo.save(modelo);

		session.setAttribute("modificacion", "anadirModelo");
		return "redirect:/admin";
	}
}
