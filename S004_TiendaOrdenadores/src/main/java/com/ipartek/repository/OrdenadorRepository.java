package com.ipartek.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.model.Ordenador;

@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ordenadores WHERE marca = :marca_id", nativeQuery = true)
	void eliminarOrdenadoresPorMarca(@Param("marca_id") int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ordenadores WHERE modelo = :modelo_id", nativeQuery = true)
	void eliminarOrdenadoresPorModelo(@Param("modelo_id") int id);
	
	
	@Query(value = "SELECT * FROM ordenadores where numeroSerie= :numeroSerie",nativeQuery = true)
	Page<Ordenador> buscarProducto(Pageable pageable, String numeroSerie);
	
	



	public default Page<Ordenador> findPage(int pageNumber){
	    Pageable pageable = PageRequest.of(pageNumber - 1,5);
	    return findAll(pageable);
	}
	public default Page<Ordenador> findPageB(int pageNumber,String numeroSerie){
	    Pageable pageable = PageRequest.of(pageNumber - 1,5);
	    return buscarProducto(pageable,numeroSerie);
	}
}

