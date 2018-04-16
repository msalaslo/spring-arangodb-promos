package com.msl.data.arangodb.promo.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.EntidadPromocionada;
 
public interface EntidadPromocionadaRepository extends ArangoRepository<EntidadPromocionada> {
	
	public Iterable<EntidadPromocionada> findAll();
 
}