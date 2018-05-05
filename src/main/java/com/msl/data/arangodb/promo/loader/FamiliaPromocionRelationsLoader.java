package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.FamiliaPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.FamiliaPromocionRepository;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;


@Component
public class FamiliaPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader{
	
	@Autowired
	private FamiliaRepository familiaRepository;
	
	@Autowired
	private FamiliaPromocionRepository familiaPromoRepo;
	
	@Override
	public void deletePromociones() {
		familiaPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		super.loadPromociones(EntityUtils.toPromocionable(familiaRepository.findAll()));
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		familiaPromoRepo.save(new FamiliaPromocion((Familia)promocionable, promocion));
	}
}
