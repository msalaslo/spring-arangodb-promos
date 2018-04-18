package com.msl.data.arangodb.promo.loader;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.ProductoPromocionReposiroty;
import com.msl.data.arangodb.promo.repository.ProductoRepository;
import com.msl.data.arangodb.promo.repository.PromocionRepository;
import com.msl.data.arangodb.promo.util.Util;


@Component
public class ProductoPromocionRelationsLoader implements IRepositoryLoader{
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private PromocionRepository promocionRepo;
	
	@Autowired
	private ProductoPromocionReposiroty productoPromoRepo;

	public void fullLoad() {
		// first create some relations for the productos and promos
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		for(Promocion promocion: promociones) {
			for (Producto producto : productos) {
				productoPromoRepo.save(new ProductoPromocion(producto, promocion));
			}
		}
	}
	
	public void onePromoLoad() {
		// first create some relations for the marcas and promos
		Iterable<Producto> productos = productoRepo.findAll();
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Promocion promocion = promociones.iterator().next();
		for (Producto producto : productos) {		
				productoPromoRepo.save(new ProductoPromocion(producto, promocion));
		}	
				
		productos = productoRepo.findAll();
		for (Producto producto : productos) {		
			promociones = producto.getPromociones();
			promociones.forEach(System.out::println);
		}
	}
	
	public void sharePromocionesLoad() {
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Iterable<Producto> productos = productoRepo.findAll();
		Iterator<Promocion> itePromociones = promociones.iterator();
		int numProductos = Util.getSize(productos);
		int numPromociones = Util.getSize(promociones);
		int section = numProductos/numPromociones;
		int cont = 0;
		Promocion promocion = itePromociones.next();
		for (Producto producto : productos) {			
			//Vamos avanzando la promociones a un conjunto equitativo de productos
			if(cont == section && numProductos > numPromociones && itePromociones.hasNext()) {
				promocion = itePromociones.next();
				cont = 0;
			}else {
				cont++;
			}			
//			System.out.println("Asociando el producto " + producto + " a la promocion " + promocion );
			productoPromoRepo.save(new ProductoPromocion(producto, promocion));
		}	
				
//		productos = productoRepo.findAll();
//		for (Producto productoConPromocion : productos) {
//			Collection<Promocion> promocionesProd = productoConPromocion.getPromociones();
//			System.out.println("producto:" + productoConPromocion + " promocion:" + promocionesProd);
//		}
	}
	
	public void deleteAll() {
		productoPromoRepo.deleteAll();
	}
}
