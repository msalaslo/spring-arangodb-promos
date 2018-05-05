package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.EmpresaPromocion;
import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.EmpresaPromocionRepository;
import com.msl.data.arangodb.promo.repository.EmpresaRepository;


@Component
public class EmpresaPromocionRelationsLoader extends AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader{
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaPromocionRepository empresaPromoRepo;
	
	@Override
	public void deletePromociones() {
		empresaPromoRepo.deleteAll();
	}
	
	@Override
	public void loadPromociones() {
		super.loadPromociones(EntityUtils.toPromocionable(empresaRepository.findAll()));
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		empresaPromoRepo.save(new EmpresaPromocion((Empresa)promocionable, promocion));
	}

}
