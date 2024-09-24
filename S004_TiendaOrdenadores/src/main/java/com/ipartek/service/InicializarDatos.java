package com.ipartek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ipartek.model.Privilegio;
import com.ipartek.model.Usuario;
import com.ipartek.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class InicializarDatos {
	

	
	@Autowired
	private UsuarioRepository usuarioRepo ;
	
	@PostConstruct
	@Transactional
	public void inicializarDatos() {
	
		usuarioRepo.save(new Usuario(2,"2","d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35",Privilegio.ADMIN));
		usuarioRepo.save(new Usuario(1,"1","6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",Privilegio.USUARIO));
	
	}

}
