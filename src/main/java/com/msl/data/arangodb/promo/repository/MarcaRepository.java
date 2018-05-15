package com.msl.data.arangodb.promo.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface MarcaRepository extends ArangoRepository<Marca> {
	public Optional<Marca> findById(String id);
	public Iterable<Marca> findByCmarmuma(String cmarmuma);
	public Iterable<Marca> findByName(String name);
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByCmarmuma(String cmarmuma);
	//No funciona con arango, da un error al intentar castear a Stream
//	@Query("FOR v IN marcas RETURN v")
//	public Stream<Marca> findAllAsStream();
}