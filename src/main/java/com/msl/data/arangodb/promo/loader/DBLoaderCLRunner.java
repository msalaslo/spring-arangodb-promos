package com.msl.data.arangodb.promo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.data.arangodb.promo.repository.MarcaRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class DBLoaderCLRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(DBLoaderCLRunner.class.getName());
	
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
	ProductoCentroRelationsLoader productoCentroLoader;
	
	@Autowired
	ProductoFamiliaRelationsLoader productoFamiliaLoader;
	
	@Autowired
	ProductoMarcaRelationsLoader productoMarcaLoader;
	
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
		IRelacionableRepositoryLoader[] relacionableLoaders = {centroEmpresaLoader, productoCentroLoader, productoMarcaLoader, productoFamiliaLoader};
		IPromocionableRepositoryLoader[] promocionLoaders = {empresaPromocionLoader, centroPromocionLoader, marcaPromocionLoader, familiaPromocionLoader, productoPromocionLoader};
		deleteRepositories(loaders);
		deletePromociones(promocionLoaders);
		deleteRelaciones(relacionableLoaders);
		loadRepositories(loaders);		
		loadPromociones(promocionLoaders);
		loadRelaciones(relacionableLoaders);
	}
	
	private void deleteRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			logger.info("Borrando datos sobre " + loader);
			loader.deleteAll();
		}
	}
	
	private void loadRepositories(IRepositoryLoader[] loaders) {
		for (IRepositoryLoader loader : loaders) {
			logger.info("Cargando datos sobre " + loader);
			loader.load();
		}
	}
	
	private void deleteRelaciones(IRelacionableRepositoryLoader[] loaders) {
		for (IRelacionableRepositoryLoader loader : loaders) {
			logger.info("Borrando relaciones sobre " + loader);
			loader.deleteRelaciones();
		}
	}
	
	private void loadRelaciones(IRelacionableRepositoryLoader[] loaders) {
		for (IRelacionableRepositoryLoader loader : loaders) {
			logger.info("Cargando relaciones sobre " + loader);
			loader.loadRelaciones();
		}
	}
	
	private void deletePromociones(IPromocionableRepositoryLoader[] loaders) {
		for (IPromocionableRepositoryLoader loader : loaders) {
			logger.debug("Borrado promociones de " + loader);
			loader.deletePromociones();
		}
	}
	
	private void loadPromociones(IPromocionableRepositoryLoader[] loaders) {
		for (IPromocionableRepositoryLoader loader : loaders) {
			logger.debug("Cargando promociones sobre " + loader);
			loader.loadPromociones();
		}
	}
}
