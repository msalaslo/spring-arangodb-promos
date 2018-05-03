package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.EmpresaPromocion;
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
		Iterable<Empresa> promocionables = empresaRepository.findAll();
		List<Promocionable> lista = new ArrayList<Promocionable>();
		for (Empresa empresa : promocionables) {
			lista.add(empresa);
		}
		super.loadPromociones(lista);
	}
	
	@Override
	public void save(Promocionable promocionable, Promocion promocion) {
		empresaPromoRepo.save(new EmpresaPromocion((Empresa)promocionable, promocion));
	}

}
