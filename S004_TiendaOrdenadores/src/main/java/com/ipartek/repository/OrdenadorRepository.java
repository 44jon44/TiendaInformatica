package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Ordenador;



@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Integer>{
	
//	@Query(value = "SELECT * FROM productos WHERE nombre = :producto AND genero_id = :genero AND categoria_id = :categoria AND talla_id = :talla", nativeQuery = true)
//	List<Producto> buscarProducto(@Param("producto") String prod, @Param("genero") int genero, @Param("categoria") int categoria, @Param("talla") int talla);
//	
//	@Query(value = "SELECT * FROM productos WHERE visible=1", nativeQuery = true)
//	List<Producto> buscarTodosVisibles();
//
//	@Query(value = "SELECT * FROM productos WHERE visible=1 and categoria_id=1", nativeQuery = true)
//	List<Producto> buscarCamisetasVisibles();
//	
//	@Query(value = "SELECT * FROM productos WHERE visible=1 and categoria_id=2", nativeQuery = true)
//	List<Producto> buscarFaldasVisibles();
//	
//	@Query(value = "SELECT * FROM productos WHERE visible=1 and categoria_id=3", nativeQuery = true)
//	List<Producto> buscarJerseysVisibles();
//	
//	@Query(value = "SELECT * FROM productos WHERE visible=1 and categoria_id=4", nativeQuery = true)
//	List<Producto> buscarPantalonesVisibles();
}
