package com.msl.data.arangodb.promo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;

@Service
public class FamiliaService {

	@Autowired
	FamiliaRepository repository;
	
    public Optional<Familia> findByid(String id){
    	return repository.findById(id);
    }
    
    public Iterable<Familia> findByCfamilia(String cfamilia){
    	return repository.findByCfamilia(cfamilia);
    }
    
    public Iterable<Familia> findByName(String name){
    	return repository.findByName(name);
    }
    
    public Iterable<Promocion> findPromocionesById(String id){
    	return repository.findPromocionesById(id);
    }
    
    public Iterable<Promocion> findPromocionesByCfamilia(String cfamilia){
    	return repository.findPromocionesByCfamilia(cfamilia);
    }
    
    public Iterable<Promocion> findPromocionesByName(String name){
    	return repository.findPromocionesByName(name);
    }

	public Familia save(Familia product) {
		return repository.save(product);
	}
}
