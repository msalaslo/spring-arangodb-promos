package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.repository.MarcaRepository;

@Component
public class MarcaLoader implements IRepositoryLoader{

	@Autowired
	private MarcaRepository repository;
	
	@Override
	public void deleteAll() {
	    repository.deleteAll();
	}

	public void load(final int numMarcas) {    
	    Collection<Marca> createMarcas = createMarcas(numMarcas);
//	    System.out.println(String.format("Save %s additional marcas", numMarcas));
	    repository.save(createMarcas);
	     
//	    Iterable<Marca> all = repository.findAll();
//	    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
//	    System.out.println(String.format("A total of %s marcas are persisted in the database", count));
	    
//	    printAllMarcasByName(repository);
	}
	
	public static void printAllMarcasByName(MarcaRepository repository) {
		System.out.println("## Return all marcas sorted by name");
		Iterable<Marca> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}
	
	private static Collection<Marca> createMarcas(int numMarcas) {
		String namePrefix = "marca";
		List<Marca> marcas = new ArrayList<Marca>();
		for(int cmarmuma = 0; cmarmuma < numMarcas; cmarmuma++){
			String cmarmumaStr = String.format("%014d",Integer.valueOf(cmarmuma));
			Marca marca = new Marca(cmarmumaStr, namePrefix + cmarmuma);
			marcas.add(marca);
		}
	    return marcas;
	  }
}
