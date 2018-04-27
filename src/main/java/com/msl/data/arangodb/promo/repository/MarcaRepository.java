package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface MarcaRepository extends ArangoRepository<Marca> {
	public Optional<Marca> findById(String id);
	public Iterable<Marca> findByCmarmuma(String cmarmuma);
	public Iterable<Marca> findByName(String name);
	public Iterable<Marca> findAll();
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByCmarmuma(String cmarmuma);
}