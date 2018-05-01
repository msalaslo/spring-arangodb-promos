package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.FamiliaPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.FamiliaPromocionRepository;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;


@Component
public class FamiliaPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IRepositoryLoader{
	
	@Autowired
	private FamiliaRepository familiaRepository;
	
	@Autowired
	private FamiliaPromocionRepository familiaPromoRepo;
	
	@Override
	public void deleteAll() {
		familiaPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		Iterable<Familia> promocionables = familiaRepository.findAll();
		List<Promocionable> lista = new ArrayList<Promocionable>();
		for (Familia familia : promocionables) {
			lista.add(familia);
		}
		super.loadPromociones(lista);
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		familiaPromoRepo.save(new FamiliaPromocion((Familia)promocionable, promocion));
	}

}
