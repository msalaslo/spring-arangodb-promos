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

	public void load() {    
	    List<Familia> createFamilias = createFamilias(RepositoryConfig.NUM_FAMILIAS);
	    repository.saveAll(createFamilias);
	}
	
	private static List<Familia> createFamilias(int numFamilias) {
		String namePrefix = "familia";
		List<Familia> familias = new ArrayList<Familia>();
		for(int cfamilia = 0; cfamilia < numFamilias; cfamilia++){
			String cfamiliaStr = String.format("%03d",Integer.valueOf(cfamilia));
			Familia familia = new Familia(cfamiliaStr, namePrefix + cfamilia);
			familias.add(familia);
		}
	    return familias;
	  }
}