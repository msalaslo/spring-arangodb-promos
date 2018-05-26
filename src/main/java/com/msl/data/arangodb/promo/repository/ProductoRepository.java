package com.msl.data.arangodb.promo.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.Promocion;

public interface ProductoRepository extends ArangoRepository<Producto> {
	public Optional<Producto> findById(String id);
	public Iterable<Producto> findByReferencia(String referencia);
	public Producto findByName(String name);
	public Iterable<Producto> findAll();
	public Page<Producto> findAll(Pageable pageable);
	Iterable<Promocion> findPromocionesById(String id);
	Iterable<Promocion> findPromocionesByName(String name);
	Iterable<Promocion> findPromocionesByReferencia(String referencia);
	@Query("FOR v IN 1..10 OUTBOUND @id GRAPH 'marcaPromocion' RETURN v")
//	@Query("WITH promociones,productos,marcas,familias,centros,empresas \r\n" + 
//			"FOR v IN 1..10 \r\n" + 
//			"OUTBOUND @id \r\n" + 
//			"productoPromocion,marcaPromocion,familiaPromocion,centroPromocion,empresaPromocion,"
//			+ "productoMarca,productoFamilia,productoCentro,"
//			+ "centroEmpresa \r\n" + 
//			"RETURN v")
	Iterable<Promocion> findAllPromocionesById(@Param("id") String value);
	
//	java.lang.ClassCastException: com.msl.data.arangodb.promo.entity.Producto cannot be cast to java.util.stream.Stream
	@Query("FOR v IN productos RETURN v")
	public Stream<Producto> findAllAsStream();
}