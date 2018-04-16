package com.msl.data.arangodb.promo.repository;

import java.util.Collection;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface MarcaRepository extends ArangoRepository<Marca> {
	public Marca findById(String id);
	public Iterable<Marca> findByCmarmuma(String cmarmuma);
	public Iterable<Marca> findByName(String name);
	public Iterable<Marca> findAll();
	Collection<Promocion> findPromocionesById(String id);
	Collection<Promocion> findPromocionesByName(String name);
	Collection<Promocion> findPromocionesByCmarmuma(String cmarmuma);
}