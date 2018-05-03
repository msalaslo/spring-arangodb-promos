package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.data.arangodb.promo.repository.DBRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class DBLoaderCLRunner implements CommandLineRunner {

	public static final int NUM_EMPRESAS = 5;
	public static final int NUM_CENTROS = 10;
	public static final int NUM_FAMILIAS = 100;
	public static final int NUM_MARCAS = 1000;
	public static final int NUM_PRODUCTOS = 10000;
	public static final int NUM_PROMOS = 10;

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
	CentroEmpresaRelationsLoader centroEmpresaLoader;

	@Autowired
	ProductoMarcaRelationsLoader productoMarcaLoader;
	
	@Autowired
	ProductoCentroRelationsLoader productoCentroLoader;
	
	@Autowired
	ProductoFamiliaRelationsLoader productoFamiliaLoader;
	
	@Autowired
	EmpresaPromocionRelationsLoader empresaPromocionLoader;
	
	@Autowired
	CentroPromocionRelationsLoader centroPromocionLoader;
	
	@Autowired
	FamiliaPromocionRelationsLoader familiaPromocionLoader;

	@Autowired
	ProductoPromocionRelationsLoader productoPromocionLoader;

	@Autowired
	MarcaPromocionRelationsLoader marcaPromocionLoader;
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Override
	public void run(final String... args) throws Exception {
		IRepositoryLoader[] loaders = {empresaLoader, centroLoader, familiaLoader, marcaLoader, productoLoader, promocionLoader};
		IRelacionableRepositoryLoader[] relacionableLoaders = {centroEmpresaLoader, productoCentroLoader, productoMarcaLoader};
		IPromocionableRepositoryLoader[] promocionLoaders = {empresaPromocionLoader, centroPromocionLoader, marcaPromocionLoader, productoPromocionLoader};
		deleteRepositories(loaders);
		deletePromociones(promocionLoaders);
		deleteRelaciones(relacionableLoaders);
		loadRepositories(loaders);		
		loadPromociones(promocionLoaders);
		loadRelaciones(relacionableLoaders);
	}
	
	private void deleteRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			System.out.println("Borrando datos sobre " + loader);
			loader.deleteAll();
		}
	}
	
	private void loadRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			System.out.println("Cargando datos sobre " + loader);
			loader.load();
		}
	}
	
	private void deleteRelaciones(IRelacionableRepositoryLoader[] loaders) {
		for (IRelacionableRepositoryLoader loader : loaders) {
			System.out.println("Borrando relaciones sobre " + loader);
			loader.deleteRelaciones();
		}
	}
	
	private void loadRelaciones(IRelacionableRepositoryLoader[] loaders) {
		for (IRelacionableRepositoryLoader loader : loaders) {
			System.out.println("Cargando relaciones sobre " + loader);
			loader.loadRelaciones();
		}
	}
	
	private void deletePromociones(IPromocionableRepositoryLoader[] loaders) {
		for (IPromocionableRepositoryLoader loader : loaders) {
			System.out.println("Borrado promociones de " + loader);
			loader.deletePromociones();
		}
	}
	
	private void loadPromociones(IPromocionableRepositoryLoader[] loaders) {
		for (IPromocionableRepositoryLoader loader : loaders) {
			System.out.println("Cargando promociones sobre " + loader);
			loader.loadPromociones();
		}
	}
}
