package com.msl.data.arangodb.promo.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Producto;

public interface ProductoRepository extends ArangoRepository<Producto> {
	public Producto findById(String id);
	public Iterable<Producto> findByReferencia(String referencia);
	public Producto findByName(String name);
	public Iterable<Producto> findAll();
	Iterable<Producto> findByPromocionesCodpromoci(String codpromoci);
}