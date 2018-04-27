package com.msl.data.arangodb.promo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	MarcaRepository repository;
	
    public Optional<Marca> findByid(String id){
    	return repository.findById(id);
    }
    
    public Iterable<Marca> findByCmarmuma(String cmarmuma){
    	return repository.findByCmarmuma(cmarmuma);
    }
    
    public Iterable<Promocion> findPromocionesById(String id){
    	return repository.findPromocionesById(id);
    }
    
    public Iterable<Promocion> findPromocionesByCmarmuma(String cmarmuma){
    	return repository.findPromocionesByCmarmuma(cmarmuma);
    }
    
    public Iterable<Promocion> findPromocionesByName(String name){
    	return repository.findPromocionesByName(name);
    }

	public Marca save(Marca product) {
		return repository.save(product);
	}
}
