package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.data.arangodb.promo.repository.DBRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class DBLoaderCLRunner implements CommandLineRunner {

	public static final int NUM_MARCAS = 10;
	public static final int NUM_PRODUCTOS = 100;
	public static final int NUM_PROMOS = 5;

	@Autowired
	DBRepository db;

	@Autowired
	MarcaLoader marcaLoader;

	@Autowired
	ProductoLoader productoLoader;

	@Autowired
	PromocionLoader promocionLoader;

	@Autowired
	ProductoPromocionRelationsLoader productoPromocionLoader;

	@Autowired
	MarcaPromocionRelationsLoader marcaPromocionLoader;
	
	@Autowired
	ProductoMarcaRelationsLoader productoMarcaLoader;
	
	@Override
	public void run(final String... args) throws Exception {
		System.out.println("Borrando base de datos");
		IRepositoryLoader[] loaders = {marcaLoader, productoLoader, promocionLoader, productoPromocionLoader, marcaPromocionLoader, productoMarcaLoader};
		//db.dropDatabase();
		deleteRepositories(loaders);
		System.out.println("Cargando marcas:" + NUM_MARCAS);
		marcaLoader.load(NUM_MARCAS);
		System.out.println("Cargando productos:" + NUM_PRODUCTOS);
		productoLoader.load(NUM_PRODUCTOS);
		System.out.println("Cargando promociones:" + NUM_PROMOS);
		promocionLoader.load(NUM_PROMOS);
		System.out.println("Cargando promociones de productos");
//		productoPromocionLoader.fullLoad();
//		productoPromocionLoader.onePromoLoad();
		productoPromocionLoader.sharePromocionesLoad();
		System.out.println("Cargando promociones de marcas");
//		marcaPromocionLoader.fullLoad();
		marcaPromocionLoader.shareMarcasLoad();
		System.out.println("Cargando relaciones entre productos y marcas");
		productoMarcaLoader.shareMarcasLoad();
	}
	
	private void deleteRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			loader.deleteAll();
		}
	}
}
