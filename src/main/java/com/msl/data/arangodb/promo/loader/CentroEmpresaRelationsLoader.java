package com.msl.data.arangodb.promo.loader;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.entity.CentroEmpresa;
import com.msl.data.arangodb.promo.entity.Empresa;
import com.msl.data.arangodb.promo.repository.CentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaCentroRepository;
import com.msl.data.arangodb.promo.repository.EmpresaRepository;
import com.msl.data.arangodb.promo.util.Util;


@Component
public class CentroEmpresaRelationsLoader implements IRepositoryLoader{
	@Autowired
	private CentroRepository centroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaCentroRepository centroEmpresaRepo;

	public void fullLoad() {
		// first create some relations for the centros and empresas
		Iterable<Centro> centros = centroRepo.findAll();
		Iterable<Empresa> empresas = empresaRepo.findAll();
		for (Centro centro : centros) {
			for (Empresa empresa : empresas) {			
				centroEmpresaRepo.save(new CentroEmpresa(centro, empresa));
			}	
		}
	}
	
	public void shareEmpresasLoad() {
		// first create some relations for the centros and empresas
		Iterable<Centro> centros = centroRepo.findAll();
		Iterable<Empresa> empresas = empresaRepo.findAll();
		Iterator<Empresa> iteEmpresas = empresas.iterator();
		int numEmpresas = Util.getSize(empresas);
		int numCentros = Util.getSize(centros);
		int section = numEmpresas/numCentros;
		int cont = 0;
		Empresa empresa = iteEmpresas.next();
		for (Centro centro: centros) {			
			//Vamos asociando los centros un conjunto equitativo de empresas
			if(cont == section && iteEmpresas.hasNext()) {
				empresa = iteEmpresas.next();
				cont = 0;
			}else {
				cont++;
			}			
//			System.out.println("Asociando la empresa " + empresa + " a la centro " + centro );
			centroEmpresaRepo.save(new CentroEmpresa(centro, empresa));
		}	
	}
	
	public void deleteAll() {
		centroEmpresaRepo.deleteAll();
	}
}
