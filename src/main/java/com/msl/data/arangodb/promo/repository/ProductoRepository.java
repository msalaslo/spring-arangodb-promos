package com.msl.data.arangodb.promo.repository;

import java.util.List;
import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Producto;

public interface ProductoRepository extends ArangoRepository<Producto> {
	public Producto findById(String id);
	public Iterable<Producto> findByReferencia(String referencia);
	public Optional<Producto> findByName(String name);
	public List<Producto> findAll();
	Iterable<Producto> findByPromocionesCodpromoci(String codpromoci);
}