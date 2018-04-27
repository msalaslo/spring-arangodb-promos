package com.msl.data.arangodb.promo.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface CentroRepository extends ArangoRepository<Centro> {
	public List<Centro> findByCentroo(String centroo);
	List<Promocion> findPromocionesByCentroo(String centroo);
}