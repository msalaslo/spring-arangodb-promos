package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoFamilia;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;
import com.msl.data.arangodb.promo.repository.ProductoFamiliaRepository;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoFamiliaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRepositoryLoader{
	@Autowired
	private FamiliaRepository familiaRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoFamiliaRepository familiaProductoRepo;
	
	@Override
	public void loadRelaciones() {
		// first create some relations for the productos and familias
		Iterable<Producto> productos = productoRepo.findAll();
		Iterable<Familia> familias = familiaRepo.findAll();
		List<Relacionable> relacionables = new ArrayList<Relacionable>();
		for (Producto producto : productos) {
			relacionables.add((Relacionable)producto);
		}
		
		List<RelacionableParent> parents = new ArrayList<RelacionableParent>();
		for (Familia familia : familias) {
			parents.add((RelacionableParent)familia);
		}
		
		super.loadRelaciones(relacionables, parents);
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		familiaProductoRepo.save(new ProductoFamilia((Producto)relacionable, (Familia)parent));
	}
	
	@Override
	public void deleteAll() {
		familiaProductoRepo.deleteAll();
	}
}
