package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.repository.ProductoRepository;

@Component
public class ProductoLoader implements IRepositoryLoader{

	@Autowired
	private ProductoRepository repository;
	
	public void deleteAll() {
	    repository.deleteAll();
	}

	public void load(final int numProductos) {    
		saveProductos(numProductos);
//	    Iterable<Producto> productos = saveProductos(numProductos);
//	    System.out.println(String.format("Save %s additional productos", numProductos));
	    	     
//	    Iterable<Producto> all = repository.findAll();
//	    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
//	    System.out.println(String.format("A total of %s productos are persisted in the database", count));
	    
//	    printAllProductosByName(repository);
	}
	
	public static void printAllProductosByName(ProductoRepository repository) {
		System.out.println("## Return all productos sorted by name");
		Iterable<Producto> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}

	private void saveProductos(int numProductos) {
		int groups = 1;
		if(numProductos > 100) {
			groups = numProductos/100;
		}
		if(groups > 0) {
			for(int i=0;i<groups;i++) {
				repository.saveAll(createProductos(i, numProductos));
			}
		}
	}

	private List<Producto> createProductos(int start, int numProductos) {
		String cempresa = "123";
		String centrooo = "1234";
		String cdepartm = "1234";
		String cfamilia = "123";
		String cbarraaa = "1234";
		String ctallaec = "123";
		String cdivisio = "12";
		String cniveln = "1";
		String cfabrica = "123456";
		String cmarmuma = "12345678901234";
		String referencia = cempresa + centrooo + cdepartm + cfamilia + cbarraaa + ctallaec + cdivisio + cniveln + cfabrica + cmarmuma;
		List<Producto> productos = new ArrayList<Producto>();
		for (int i = start; i < numProductos; i++) {
			productos.add(new Producto(referencia + i , "producto" + i));
		}
	    return productos;
	}
}
