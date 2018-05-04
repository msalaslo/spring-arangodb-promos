package com.msl.data.arangodb.promo.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.CentroEmpresa;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.EntityUtil;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaCentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaRepository;


@Component
public class CentroEmpresaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaCentroRepository centroEmpresaRepo;

	@Override
	public void loadRelaciones() {
		// create some relations for the centros and empresas
		Iterable<Centro> centros = centroRepo.findAll();
		Iterable<Empresa> empresas = empresaRepo.findAll();
		List<Relacionable> relacionables = EntityUtil.centroToRelacionable(centros);
		List<RelacionableParent> parents = EntityUtil.empresaToRelacionableParent(empresas);
		super.loadRelaciones(relacionables, parents);
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		centroEmpresaRepo.save(new CentroEmpresa((Centro)relacionable, (Empresa)parent));
	}
	
	@Override
	public void deleteRelaciones() {
		centroEmpresaRepo.deleteAll();
	}
}
