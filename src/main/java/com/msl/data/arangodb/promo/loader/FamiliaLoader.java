package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;

@Component
public class FamiliaLoader implements IRepositoryLoader{

	@Autowired
	private FamiliaRepository repository;
	
	@Override
	public void deleteAll() {
	    repository.deleteAll();
	}

	public void load(final int numFamilias) {    
	    List<Familia> createFamilias = createFamilias(numFamilias);
	    repository.saveAll(createFamilias);
	}
	
	private static List<Familia> createFamilias(int numFamilias) {
		String namePrefix = "familia";
		List<Familia> marcas = new ArrayList<Familia>();
		for(int cfamilia = 0; cfamilia < numFamilias; cfamilia++){
			String cfamiliaStr = String.format("%03d",Integer.valueOf(cfamilia));
			Familia marca = new Familia(cfamiliaStr, namePrefix + cfamilia);
			marcas.add(marca);
		}
	    return marcas;
	  }
}