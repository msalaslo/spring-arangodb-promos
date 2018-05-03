package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	@Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private ProductoMarcaRepository marcaProductoRepo;
	
	@Override
	public void loadRelaciones() {
		// first create some relations for the productos and marcas
		Iterable<Producto> productos = productoRepo.findAll();
		Iterable<Marca> marcas = marcaRepo.findAll();
		List<Relacionable> relacionables = new ArrayList<Relacionable>();
		for (Producto producto : productos) {
			relacionables.add((Relacionable)producto);
		}
		
		List<RelacionableParent> parents = new ArrayList<RelacionableParent>();
		for (Marca marca : marcas) {
			parents.add((RelacionableParent)marca);
		}
		
		super.loadRelaciones(relacionables, parents);
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		marcaProductoRepo.save(new ProductoMarca((Producto)relacionable, (Marca)parent));
	}
	
	@Override
	public void deleteRelaciones() {
		marcaProductoRepo.deleteAll();
	}
}
