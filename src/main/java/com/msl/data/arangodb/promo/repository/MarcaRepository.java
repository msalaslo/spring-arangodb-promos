package com.msl.data.arangodb.promo.repository;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface MarcaRepository extends ArangoRepository<Marca> {
	public Optional<Marca> findById(String id);
	public Iterable<Marca> findByCmarmuma(String cmarmuma);
	public Iterable<Marca> findByName(String name);
	public Iterable<Promocion> findPromocionesById(String id);
	public Iterable<Promocion> findPromocionesByName(String name);
	public Iterable<Promocion> findPromocionesByCmarmuma(String cmarmuma);
	//No funciona con arango, da un error al intentar castear a Stream
//	@Query("FOR v IN marcas RETURN v")
//	public Stream<Marca> findAllAsStream();
}