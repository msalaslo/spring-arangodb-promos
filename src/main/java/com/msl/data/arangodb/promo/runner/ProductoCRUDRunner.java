package com.msl.data.arangodb.promo.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

import com.arangodb.springframework.core.ArangoOperations;
import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.repository.ProductoRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class ProductoCRUDRunner implements CommandLineRunner {

	@Autowired
	private ArangoOperations operations;
	@Autowired
	private ProductoRepository repository;

	@Override
	public void run(final String... args) throws Exception {
	    // first drop the database so that we can run this multiple times with the same dataset
//	    operations.dropDatabase();
//	    
//	    Collection<Producto> createProductos = createProductos(10);
//	    System.out.println(String.format("Save %s additional products", createProductos.size()));
//	    repository.save(createProductos);
	     
	    Iterable<Producto> all = repository.findAll();
	    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
	    System.out.println(String.format("A total of %s products are persisted in the database", count));
	    
//	    printAllProductsByName(repository);
	}
	
	public static void printAllProductsByName(ProductoRepository repository) {
		System.out.println("## Return all products sorted by name");
		Iterable<Producto> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}
	
	private static Collection<Producto> createProductos(int numProductos) {
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
		for (int i = 0; i < numProductos; i++) {
			productos.add(new Producto(referencia + i , "producto" + i));
		}
	    return productos;
	  }
}
