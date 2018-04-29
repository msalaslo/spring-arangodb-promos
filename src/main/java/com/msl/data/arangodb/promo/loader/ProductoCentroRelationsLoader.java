package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoCentro;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.ProductoCentroRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoCentroRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRepositoryLoader{
	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoCentroRepository centroProductoRepo;
	
	@Override
	public void loadRelaciones() {
		// first create some relations for the productos and centros
		Iterable<Producto> productos = productoRepo.findAll();
		Iterable<Centro> centros = centroRepo.findAll();
		List<Relacionable> relacionables = new ArrayList<Relacionable>();
		for (Producto producto : productos) {
			relacionables.add((Relacionable)producto);
		}
		
		List<RelacionableParent> parents = new ArrayList<RelacionableParent>();
		for (Centro centro : centros) {
			parents.add((RelacionableParent)centro);
		}
		
		super.loadRelaciones(relacionables, parents);
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		centroProductoRepo.save(new ProductoCentro((Producto)relacionable, (Centro)parent));
	}
	
	@Override
	public void deleteAll() {
		centroProductoRepo.deleteAll();
	}
}
