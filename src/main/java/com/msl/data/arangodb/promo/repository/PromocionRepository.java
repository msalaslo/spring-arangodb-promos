package com.msl.data.arangodb.promo.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Promocion;
 
public interface PromocionRepository extends ArangoRepository<Promocion> {
    public Promocion findById(String id);
	public Promocion findByCodpromoci(String codpromoci);
    public List<Promocion> findByCanlvnta(String canlvnta);   
    public List<Promocion> findAll();
}