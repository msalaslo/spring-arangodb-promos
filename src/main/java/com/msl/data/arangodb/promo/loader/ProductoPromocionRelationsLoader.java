package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.ProductoPromocionReposiroty;
import com.msl.data.arangodb.promo.repository.ProductoRepository;
import com.msl.data.arangodb.promo.repository.PromocionRepository;


@Component
public class ProductoPromocionRelationsLoader {
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private PromocionRepository promocionRepo;
	
	@Autowired
	private ProductoPromocionReposiroty productoPromoRepo;

	public void load() {
		// first create some relations for the productos and promos
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		for(Promocion promocion: promociones) {
			for (Producto producto : productos) {
				productoPromoRepo.save(new ProductoPromocion(producto, promocion));
			}
		}
	}
	
	public void deleteAll() {
		productoPromoRepo.deleteAll();
	}
}
