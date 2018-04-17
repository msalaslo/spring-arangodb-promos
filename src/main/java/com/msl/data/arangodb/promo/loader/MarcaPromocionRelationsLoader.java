package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.MarcaPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.MarcaPromocionRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;
import com.msl.data.arangodb.promo.repository.PromocionRepository;


@Component
public class MarcaPromocionRelationsLoader {
	@Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private PromocionRepository promocionRepo;
	
	@Autowired
	private MarcaPromocionRepository marcaPromoRepo;

	public void fullLoad() {
		// first create some relations for the marcas and promos
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Promocion> promociones = promocionRepo.findAll();
		for (Promocion promocion : promociones) {
			for (Marca marca : marcas) {
				marcaPromoRepo.save(new MarcaPromocion(marca, promocion));
			}	
		}
		
		marcas = marcaRepo.findAll();
		for (Marca marca : marcas) {
			promociones = marca.getPromociones();
			promociones.forEach(System.out::println);
		}
	}
	
	public void onePromoLoad() {
		// first create some relations for the marcas and promos
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Promocion promocion = promociones.iterator().next();
		for (Marca marca : marcas) {		
				marcaPromoRepo.save(new MarcaPromocion(marca, promocion));
		}	
				
		marcas = marcaRepo.findAll();
		for (Marca marca : marcas) {
			promociones = marca.getPromociones();
			promociones.forEach(System.out::println);
		}
	}
	
	public void deleteAll() {
		marcaPromoRepo.deleteAll();
	}
}
