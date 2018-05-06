package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface ProductoRepository extends ArangoRepository<Producto> {
	public Optional<Producto> findById(String id);
	public Iterable<Producto> findByReferencia(String referencia);
	public Producto findByName(String name);
	public Iterable<Producto> findAll();
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByReferencia(String referencia);
	@Query("FOR v IN 1..10 OUTBOUND @id GRAPH 'marcaPromocion' RETURN v")
	Iterable<Promocion> findAllPromocionesById(@Param("id") String value);
}