package com.msl.data.arangodb.promo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.CentroEmpresa;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaCentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaRepository;


@Component
public class CentroEmpresaRelationsLoader extends AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{

	private static final Logger logger = LoggerFactory.getLogger(CentroEmpresaRelationsLoader.class);

	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaCentroRepository centroEmpresaRepo;
	
	@Override
	public void loadRelaciones() {
		logger.info("Cargando relaciones de productos con marcas");
		super.loadRelacionesPaging((int)centroRepo.count(), EntityUtils.toRelacionable(centroRepo.findAll()), EntityUtils.toRelacionableParent(empresaRepo.findAll()));
		logger.info("Relaciones cargadas");	
	}

//	@Override
	public void loadRelacionesWithoutPaging() {
		super.loadRelaciones(EntityUtils.toRelacionable(centroRepo.findAll()), EntityUtils.toRelacionableParent(empresaRepo.findAll()));
	}
	
	@Override
	public void save(Relacionable relacionable, RelacionableParent parent) {
		centroEmpresaRepo.save(new CentroEmpresa((Centro)relacionable, (Empresa)parent));
	}
	
	@Override
	public Iterable<Relacionable> getPage(int page, int pageSize) {
		Page<Centro> entitiesPage = centroRepo.findAll(PageRequest.of(page, pageSize));
		return EntityUtils.toRelacionable(entitiesPage.getContent());
	}
	
	@Override
	public void deleteRelaciones() {
		centroEmpresaRepo.deleteAll();
	}
}
