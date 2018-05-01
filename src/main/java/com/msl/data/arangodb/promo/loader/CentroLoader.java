package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Centro;
import com.msl.data.arangodb.promo.repository.CentroRepository;

@Component
public class CentroLoader implements IRepositoryLoader{

	@Autowired
	private CentroRepository repository;
	
	@Override
	public void deleteAll() {
	    repository.deleteAll();
	}

	public void load(final int numCentros) {    
	    List<Centro> createCentros = createCentros(numCentros);
	    repository.saveAll(createCentros);
	}
	
	private static List<Centro> createCentros(int numCentros) {
		String namePrefix = "centro";
		List<Centro> centros = new ArrayList<Centro>();
		for(int i = 0; i < numCentros; i++){
			String ccentroStr = String.format("%04d",Integer.valueOf(i));
			Centro centro = new Centro(ccentroStr, namePrefix + i);
			centros.add(centro);
		}
	    return centros;
	  }
}