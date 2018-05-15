package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoCentro;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.ProductoCentroRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoCentroRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoCentroRepository centroProductoRepo;
	
	@Override
	public void loadRelaciones() {
		super.loadRelaciones(EntityUtils.toRelacionable(productoRepo.findAll()), EntityUtils.toRelacionableParent(centroRepo.findAll()));
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		centroProductoRepo.save(new ProductoCentro((Producto)relacionable, (Centro)parent));
	}
	
	@Override
	public void deleteRelaciones() {
		centroProductoRepo.deleteAll();
	}

	@Override
	public Iterable<Relacionable> getPage(int page, int pageSize) {
		Page<Producto> entitiesPage = productoRepo.findAll(PageRequest.of(page, pageSize));
		return EntityUtils.toRelacionable(entitiesPage.getContent());
	}
}
