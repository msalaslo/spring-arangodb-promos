package com.msl.data.arangodb.promo.loader;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.MarcaPromocion;
import com.msl.data.arangodb.promo.entity.ProductoMarca;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.MarcaPromocionRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;
import com.msl.data.arangodb.promo.repository.PromocionRepository;
import com.msl.data.arangodb.promo.service.MarcaService;
import com.msl.data.arangodb.promo.util.Util;


@Component
public class MarcaPromocionRelationsLoader implements IRepositoryLoader{
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
		
//		marcas = marcaRepo.findAll();
//		for (Marca marca : marcas) {
//			promociones = marca.getPromociones();
//			promociones.forEach(System.out::println);
//		}
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
	
	public void shareMarcasLoad() {
		// first create some relations for the marcas and promociones
		Iterable<Marca> marcas = marcaRepo.findAll();
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Iterator<Promocion> itePromociones = promociones.iterator();
		int numPromociones = Util.getSize(promociones);
		int numMarcas = Util.getSize(marcas);
		int section = numMarcas/numPromociones;
		int cont = 0;
		Promocion promocion = itePromociones.next();
		for (Marca marca : marcas) {			
			//Vamos avanzando la marcas a un conjunto equitativo de productos
			if(cont <= section && itePromociones.hasNext()) {
				promocion = itePromociones.next();
				cont = 0;
			}else {
				cont++;
			}			
//			System.out.println("Asociando la promocion " + promocion + " a la marca " + marca );
			marcaPromoRepo.save(new MarcaPromocion(marca, promocion));
		}	
				
//		marcas = marcaRepo.findAll();
//		for (Marca marcaPromocionada : marcas) {
//			promociones = marcaPromocionada.getPromociones();
//			System.out.println("marca:" + marcaPromocionada + ", promociones:" + promociones);
//		}
	}
	
	public void deleteAll() {
		marcaPromoRepo.deleteAll();
	}
}
