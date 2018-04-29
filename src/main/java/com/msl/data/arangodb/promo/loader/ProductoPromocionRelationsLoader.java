package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.entity.ProductoPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.ProductoPromocionReposiroty;
import com.msl.data.arangodb.promo.repository.ProductoRepository;


@Component
public class ProductoPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IRepositoryLoader{
	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private ProductoPromocionReposiroty productoPromoRepo;

	@Override
	public void deleteAll() {
		productoPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		Iterable<Producto> promocionables = productoRepo.findAll();
		List<Promocionable> lista = new ArrayList<Promocionable>();
		for (Producto promocionable : promocionables) {
			lista.add(promocionable);
		}
		super.loadPromociones(lista);
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		productoPromoRepo.save(new ProductoPromocion((Producto)promocionable, promocion));
	}
}
