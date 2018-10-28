package com.msl.data.arangodb.promo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.PromocionRepository;

@Service
public class PromocionService {

	@Autowired
	PromocionRepository repository;
	
    public Optional<Promocion> findByid(String id){
    	return repository.findById(id);
    }
    
    public Promocion findByCodpromoci(String codpromoci){
    	return repository.findByCodpromoci(codpromoci);
    }
    
    public Iterable<Promocion> findByCanlvnta(String canlvnta){
    	return repository.findByCanlvnta(canlvnta);
    }
    
    public Iterable<Promocion> findPromocionesByCempresa(String cempresa){
    	return repository.findPromocionesByCempresa(cempresa);
    }
    
    
	public Promocion save(Promocion promocion) {
		return repository.save(promocion);
	}
}
