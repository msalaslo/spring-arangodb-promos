package com.msl.data.arangodb.promo.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface FamiliaRepository extends ArangoRepository<Familia> {
	public List<Familia> findByCfamilia(String cfamilia);
	List<Promocion> findPromocionesByCfamilia(String cfamilia);
}