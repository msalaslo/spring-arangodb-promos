package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Familia;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.repository.FamiliaRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@Component
public class FamiliaLoader implements IRepositoryLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(FamiliaLoader.class.getName());

	@Autowired
	private FamiliaRepository repository;

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void load() {
		Collection<Familia> createFamilias = createFamilias(RepositoryConfig.NUM_FAMILIAS);
		repository.saveAll(createFamilias);
	}
	
	public static void printAllByName(MarcaRepository repository) {
		Iterable<Marca> allSorted = repository.findAll(new Sort(Sort.Direction.ASC, "name"));
		allSorted.forEach(item -> logger.debug(item.toString()));
	}

	private static Collection<Familia> createFamilias(int numFamilias) {
		String namePrefix = "familia";
		List<Familia> familias = new ArrayList<Familia>();
		for (int cfamilia = 0; cfamilia < numFamilias; cfamilia++) {
			String cfamiliaStr = String.format("%03d", Integer.valueOf(cfamilia));
			Familia familia = new Familia(cfamiliaStr, namePrefix + cfamilia);
			familias.add(familia);
		}
		return familias;
	}
}