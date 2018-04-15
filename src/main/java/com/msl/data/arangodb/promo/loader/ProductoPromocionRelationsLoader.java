package com.msl.data.arangodb.promo.loader;

import java.util.Arrays;

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
		System.out.println("# Relations");
		String codpromoci = "CODPROMO1";

		// first create some relations for the productos and promos
		Producto prod1 = productoRepo.findByName("producto1");
		Producto prod2 = productoRepo.findByName("producto2");
		Promocion promo1 = promocionRepo.findByCodpromoci(codpromoci);
		
		productoPromoRepo.save(Arrays.asList(new ProductoPromocion(prod1, promo1), new ProductoPromocion(prod2, promo1)));
	
		System.out.println(String.format("## These are the promociones of producto %s:", prod1));
		prod1.getPromociones().forEach(System.out::println);

		System.out.println("## These are the productos promocionados");
		Iterable<Producto> productosPromocionados = productoRepo.findByPromocionesCodpromoci(codpromoci);
		productosPromocionados.forEach(System.out::println);
	}
}
