package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.MarcaPromocion;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.MarcaPromocionRepository;


@Component
public class MarcaPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IRepositoryLoader{
	
	@Autowired
	private MarcaPromocionRepository marcaPromoRepo;
	
	public void deleteAll() {
		marcaPromoRepo.deleteAll();
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		marcaPromoRepo.save(new MarcaPromocion((Marca)promocionable, promocion));
	}

}
