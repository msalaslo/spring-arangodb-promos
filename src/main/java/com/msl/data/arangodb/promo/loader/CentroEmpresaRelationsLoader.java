package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.CentroEmpresa;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaCentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaRepository;


@Component
public class CentroEmpresaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRepositoryLoader{
	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaCentroRepository centroEmpresaRepo;

	@Override
	public void loadRelaciones() {
		// first create some relations for the centros and empresas
		Iterable<Centro> centros = centroRepo.findAll();
		Iterable<Empresa> empresas = empresaRepo.findAll();
		List<Relacionable> relacionables = new ArrayList<Relacionable>();
		for (Centro centro : centros) {
			relacionables.add((Relacionable)centro);
		}
		
		List<RelacionableParent> parents = new ArrayList<RelacionableParent>();
		for (Empresa empresa : empresas) {
			parents.add((RelacionableParent)empresa);
		}
		
		super.loadRelaciones(relacionables, parents);
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		centroEmpresaRepo.save(new CentroEmpresa((Centro)relacionable, (Empresa)parent));
	}
	
	@Override
	public void deleteAll() {
		centroEmpresaRepo.deleteAll();
	}
}
