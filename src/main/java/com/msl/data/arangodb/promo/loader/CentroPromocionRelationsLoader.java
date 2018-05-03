package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.CentroPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.CentroPromocionRepository;
import com.msl.data.arangodb.promo.repository.CentroRepository;


@Component
public class CentroPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader{
	
	@Autowired
	private CentroRepository centroRepository;
	
	@Autowired
	private CentroPromocionRepository centroPromoRepo;
	
	@Override
	public void deletePromociones() {
		centroPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		Iterable<Centro> promocionables = centroRepository.findAll();
		List<Promocionable> lista = new ArrayList<Promocionable>();
		for (Centro centro : promocionables) {
			lista.add(centro);
		}
		super.loadPromociones(lista);
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		centroPromoRepo.save(new CentroPromocion((Centro)promocionable, promocion));
	}

}
