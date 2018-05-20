package com.msl.data.arangodb.promo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoFamilia;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;
import com.msl.data.arangodb.promo.repository.ProductoFamiliaRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoFamiliaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoFamiliaRelationsLoader.class);

	@Autowired
	private FamiliaRepository familiaRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoFamiliaRepository familiaProductoRepo;
	
	@Override
	public void loadRelaciones() {
		logger.info("Cargando relaciones de productos con familias");
		super.loadRelaciones(EntityUtils.toRelacionable(productoRepo.findAll()), EntityUtils.toRelacionableParent(familiaRepo.findAll()));
		logger.info("Relaciones cargadas");	
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		familiaProductoRepo.save(new ProductoFamilia((Producto)relacionable, (Familia)parent));
	}
	
	@Override
	public Iterable<Relacionable> getPage(int page, int pageSize) {
		Page<Producto> entitiesPage = productoRepo.findAll(PageRequest.of(page, pageSize));
		return EntityUtils.toRelacionable(entitiesPage.getContent());
	}
	
	@Override
	public void deleteRelaciones() {
		logger.info("Borrando relaciones entre familias y productos");
		familiaProductoRepo.deleteAll();
	}
}
