package com.ipartek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipartek.model.Ordenador;
import com.ipartek.repository.OrdenadorRepository;

import java.util.List;

@Service
public class OrdenadorService {

	@Autowired
	private OrdenadorRepository ordenadoresRepo;

    public List<Ordenador> findAll(){
        return ordenadoresRepo.findAll();
    }


	public Page<Ordenador> findPage(int pageNumber){
	    Pageable pageable = PageRequest.of(pageNumber - 1,5);
	    return ordenadoresRepo.findAll(pageable);
	}
}
