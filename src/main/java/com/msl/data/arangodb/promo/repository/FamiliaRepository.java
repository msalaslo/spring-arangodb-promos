package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface FamiliaRepository extends ArangoRepository<Familia> {
	public Optional<Familia> findById(String id);
	public Iterable<Familia> findByCfamilia(String cfamilia);
	public Iterable<Familia> findByName(String name);
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByCfamilia(String cfamilia);
}