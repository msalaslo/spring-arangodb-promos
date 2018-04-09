package com.msl.data.arangodb.promo.loader;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoPromocion;
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

		// first create some relations for the Starks and Lannisters
		productoRepo.findByName("producto1").ifPresent(prod1 -> {
			productoRepo.findByName("produto2").ifPresent(prod2 -> {
				promocionRepo.findByCodpromoci(codpromoci).ifPresent(promo1 -> {
					productoPromoRepo.save(Arrays.asList(new ProductoPromocion(prod1, promo1), new ProductoPromocion(prod2, promo1)));
				});
			});
		});

		productoRepo.findByName("producto1").ifPresent(prod1 -> {
			System.out.println(String.format("## These are the promociones of %s:", prod1));
			prod1.getPromociones().forEach(System.out::println);
		});

		System.out.println("## These are the productos promocionados");
		Iterable<Producto> productosPromocionados = productoRepo.findByPromocionesCodpromoci(codpromoci);
		productosPromocionados.forEach(System.out::println);
	}
}
