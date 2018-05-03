package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.MarcaPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.MarcaPromocionRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@Component
public class MarcaPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader{

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MarcaPromocionRepository marcaPromoRepo;
	
	@Override
	public void deletePromociones() {
		marcaPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		Iterable<Marca> promocionables = marcaRepository.findAll();
		List<Promocionable> lista = new ArrayList<Promocionable>();
		for (Marca marca : promocionables) {
			lista.add(marca);
		}
		super.loadPromociones(lista);
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		marcaPromoRepo.save(new MarcaPromocion((Marca)promocionable, promocion));
	}

}
