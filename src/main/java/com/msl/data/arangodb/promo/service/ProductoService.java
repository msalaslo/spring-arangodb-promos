package com.msl.data.arangodb.promo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository repository;
	
    public Producto findByid(String id){
    	return repository.findById(id);
    }
    
    public Iterable<Producto> findByReferencia(String referencia){
    	return repository.findByReferencia(referencia);
    }

	public Producto save(Producto product) {
		return repository.save(product);
	}
}
