package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
	

}
