package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.data.arangodb.promo.repository.DBRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class DBLoaderCLRunner implements CommandLineRunner {

	public static final int NUM_EMPRESAS = 10;
	public static final int NUM_CENTROS = 100;
	public static final int NUM_FAMILIAS = 10;
	public static final int NUM_MARCAS = 100;
	public static final int NUM_PRODUCTOS = 100;
	public static final int NUM_PROMOS = 100;

	@Autowired
	DBRepository db;
	
	@Autowired
	EmpresaLoader empresaLoader;
	
	@Autowired
	CentroLoader centroLoader;
	
	@Autowired
	FamiliaLoader familiaLoader;

	@Autowired
	MarcaLoader marcaLoader;

	@Autowired
	ProductoLoader productoLoader;

	@Autowired
	PromocionLoader promocionLoader;
	
	@Autowired
	EmpresaPromocionRelationsLoader empresaPromocionLoader;
	
	@Autowired
	CentroPromocionRelationsLoader centroPromocionLoader;

	@Autowired
	ProductoPromocionRelationsLoader productoPromocionLoader;

	@Autowired
	MarcaPromocionRelationsLoader marcaPromocionLoader;
	
	@Autowired
	CentroEmpresaRelationsLoader centroEmpresaLoader;
	
	@Autowired
	ProductoMarcaRelationsLoader productoMarcaLoader;
	
	@Autowired
	ProductoCentroRelationsLoader productoCentroLoader;
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Override
	public void run(final String... args) throws Exception {
		System.out.println("Borrando base de datos");
		IRepositoryLoader[] loaders = {empresaLoader, centroLoader, familiaLoader, marcaLoader, productoLoader, promocionLoader, 
				empresaPromocionLoader, centroPromocionLoader, marcaPromocionLoader, productoPromocionLoader, 
				centroEmpresaLoader, productoCentroLoader, productoMarcaLoader 
				};
		//db.dropDatabase();
		deleteRepositories(loaders);
		System.out.println("Cargando empresas:" + NUM_EMPRESAS);
		empresaLoader.load(NUM_EMPRESAS);
		System.out.println("Cargando centros:" + NUM_CENTROS);
		centroLoader.load(NUM_CENTROS);		
		System.out.println("Cargando familias:" + NUM_FAMILIAS);
		familiaLoader.load(NUM_FAMILIAS);
		System.out.println("Cargando marcas:" + NUM_MARCAS);
		marcaLoader.load(NUM_MARCAS);
		System.out.println("Cargando productos:" + NUM_PRODUCTOS);
		productoLoader.load(NUM_PRODUCTOS);
		System.out.println("Cargando promociones:" + NUM_PROMOS);
		promocionLoader.load(NUM_PROMOS);
		
		System.out.println("Cargando promociones de empresas");
		empresaPromocionLoader.loadPromociones();
		System.out.println("Cargando promociones de centros");
		centroPromocionLoader.loadPromociones();
		System.out.println("Cargando promociones de productos");
		productoPromocionLoader.loadPromociones();
		System.out.println("Cargando promociones de marcas");
		marcaPromocionLoader.loadPromociones();
		
		System.out.println("Cargando relaciones entre centros y empresas");
		centroEmpresaLoader.loadRelaciones();
		System.out.println("Cargando relaciones entre productos y marcas");
		productoMarcaLoader.loadRelaciones();
		System.out.println("Cargando relaciones entre productos y centros");
		productoCentroLoader.loadRelaciones();
	}
	
	private void deleteRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			loader.deleteAll();
		}
	}
}
