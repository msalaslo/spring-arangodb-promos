package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface EmpresaRepository extends ArangoRepository<Empresa> {
	public Optional<Empresa> findById(String id);
	public Iterable<Empresa> findByCempresa(String cempresa);
	public Iterable<Empresa> findByName(String cempresa);
	public Iterable<Promocion> findPromocionesByCempresa(String cempresa);
	public Iterable<Promocion> findPromocionesById(String id);
	public Iterable<Promocion> findPromocionesByName(String name);
}