package com.msl.data.arangodb.promo.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Promocion;
 
public interface PromocionRepository extends ArangoRepository<Promocion> {
    public Promocion findById(String id);
	public Promocion findByCodpromoci(String codpromoci);
    public Iterable<Promocion> findByCanlvnta(String canlvnta);   
    public Iterable<Promocion> findAll();
}