package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface CentroRepository extends ArangoRepository<Centro> {
	public Optional<Centro> findById(String id);
	public Iterable<Centro> findByCentroo(String centroo);
	public Iterable<Centro> findByName(String centroo);
	public Iterable<Promocion> findPromocionesByCentroo(String centroo);
	public Iterable<Promocion> findPromocionesById(String id);
	public Iterable<Promocion> findPromocionesByName(String name);
}