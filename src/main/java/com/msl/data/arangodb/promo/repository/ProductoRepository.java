package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface ProductoRepository extends ArangoRepository<Producto> {
	public Optional<Producto> findById(String id);
	public Iterable<Producto> findByReferencia(String referencia);
	public Producto findByName(String name);
	public Iterable<Producto> findAll();
	@Query("FOR v IN productos RETURN v")
	public Iterable<Producto> findAllAsStream();
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByReferencia(String referencia);
//	@Query("FOR v IN 1..10 OUTBOUND @id GRAPH 'marcaPromocion' RETURN v")
	@Query("WITH promociones,productos,marcas \r\n" + 
			"FOR v IN 1..10 \r\n" + 
			"OUTBOUND @id \r\n" + 
			"productoPromocion,productoMarca,productoCentro,centroPromocion \r\n" + 
			"RETURN v")
	Iterable<Promocion> findAllPromocionesById(@Param("id") String value);
}