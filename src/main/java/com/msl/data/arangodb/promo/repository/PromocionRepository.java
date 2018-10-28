package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Promocion;
 
public interface PromocionRepository extends ArangoRepository<Promocion> {
    public Optional<Promocion> findById(String id);
	public Promocion findByCodpromoci(String codpromoci);
    public Iterable<Promocion> findByCanlvnta(String canlvnta);   
    public Iterable<Promocion> findPromocionesByCempresa(String cempresa);      
    public Iterable<Promocion> findAll();
    public long count();
}