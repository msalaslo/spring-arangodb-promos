package com.msl.data.arangodb.promo.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface EmpresaRepository extends ArangoRepository<Empresa> {
	public List<Empresa> findByCempresa(String cempresa);
	List<Promocion> findPromocionesByCempresa(String cempresa);
}