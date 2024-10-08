package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Marca;



@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query(value = "SELECT max(id)+1 FROM marcas", nativeQuery = true)
    Integer findMaxId();
}
