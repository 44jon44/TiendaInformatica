package com.ipartek.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.auxiliares.AdvancedLogger;
import com.ipartek.auxiliares.Hashing;
import com.ipartek.model.Privilegio;
import com.ipartek.model.Usuario;
import com.ipartek.repository.MarcaRepository;
import com.ipartek.repository.OrdenadorRepository;
import com.ipartek.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {
	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);

	@Autowired
	private OrdenadorRepository productosRepo;

	@Autowired
	private MarcaRepository categoriasRepo;

	@Autowired
	private UsuarioRepository usuariosRepo;

	@RequestMapping("/formLogin")
	public String formLogin(Model model, HttpSession session) {
		if (session.getAttribute("rol").equals(Privilegio.ADMIN)) {
			
            return "redirect:/admin";
		}
		model.addAttribute("obj_usuario", new Usuario());

		return "login";
	}

	@RequestMapping("/login")
	public String login(Model model, @ModelAttribute(value = "obj_usuario") Usuario user, HttpSession session) {	
	    // Ensure session attribute exists or set default value for this user
	    Integer intentos = (Integer) session.getAttribute("Intentos_" + user.getNombre());
	    if (intentos == null) {
	        intentos = 0;
	    }
	            
        logger.info("Se ha intentado iniciar sesion con el usuario: "+user.getNombre()+" con la contraseña: "+user.getContraseña());
        
	    // Fetch all users
	    List<Usuario> usuarios = usuariosRepo.findAll();
	    
	    for (Usuario elem : usuarios) {
	        // Check if username and password match
	        if (elem.getNombre().equals(user.getNombre()) && elem.getContraseña().equals(Hashing.hash(user.getContraseña()))) {

	            // Reset intentos after a successful login
	            session.setAttribute("Intentos_" + user.getNombre(), 0);

	            session.setAttribute("rol", elem.getPriv());

	            // Redirect to admin if the user is an admin
	            if (elem.getPriv().equals(Privilegio.ADMIN)) {
	            	return "redirect:/admin";
	            }
	            if(elem.getPriv().equals(Privilegio.USUARIO)) {
	            // Redirect to index if user is not admin (default behavior)

	            return "redirect:/index";
	            }else if (elem.getPriv().equals(Privilegio.BLOQUEADO)) {
	    	        return "redirect:https://www.google.es/";

				}
	        }
	        session.setAttribute("usuario", user.getNombre());
	    }

	    // If no users match, increment attempts and handle blocking
	    intentos++;
	    session.setAttribute("Intentos_" + user.getNombre(), intentos);

	    if (intentos > 3) {
	        // Fetch the actual user from repository and update the privilege
	        Usuario blockedUser = usuariosRepo.findByNombre(user.getNombre());
	        if (blockedUser != null) {
	            blockedUser.setPriv(Privilegio.BLOQUEADO);
	            usuariosRepo.save(blockedUser);
	        }       
	        session.setAttribute("rol", Privilegio.BLOQUEADO);
	        return "redirect:https://www.google.es/";
	    }

	    // If login fails, return to login page with an error message
	    model.addAttribute("loginError", "Invalid username or password. Attempts: " + intentos);
	    return "login";
	}



}
