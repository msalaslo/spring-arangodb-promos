package com.msl.data.arangodb.promo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoMarca;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.MarcaRepository;
import com.msl.data.arangodb.promo.repository.ProductoMarcaRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoMarcaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoMarcaRelationsLoader.class);

	@Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoMarcaRepository marcaProductoRepo;
	
	@Override
	public void loadRelaciones() {
		logger.info("Cargando relaciones de productos con marcas");
		super.loadRelacionesPaging((int)productoRepo.count(), EntityUtils.toRelacionable(productoRepo.findAll()), EntityUtils.toRelacionableParent(marcaRepo.findAll()));
		logger.info("Relaciones cargadas");	
	}
	
	public void loadRelacionesWithoutPaging() {
		super.loadRelaciones(EntityUtils.toRelacionable(productoRepo.findAll()), EntityUtils.toRelacionableParent(marcaRepo.findAll()));
	}
	
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		marcaProductoRepo.save(new ProductoMarca((Producto)relacionable, (Marca)parent));
	}
	
	@Override
	public Iterable<Relacionable> getPage(int page, int pageSize) {
		Page<Producto> entitiesPage = productoRepo.findAll(PageRequest.of(page, pageSize));
		return EntityUtils.toRelacionable(entitiesPage.getContent());
	}
	
	@Override
	public void deleteRelaciones() {
		logger.info("Borrando relaciones entre marcas y productos");
		marcaProductoRepo.deleteAll();
	}
}
