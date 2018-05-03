package com.msl.data.arangodb.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.Producto;
import com.msl.data.arangodb.promo.repository.ProductoRepository;

@Component
public class ProductoLoader implements IRepositoryLoader{
	
	Logger logger = LoggerFactory.getLogger(ProductoLoader.class.getName());


	@Autowired
	private ProductoRepository repository;
	
	public void deleteAll() {
	    repository.deleteAll();
	}

	public void load() {    
		saveProductos(RepositoryConfig.NUM_PRODUCTOS, 0);
	}
	
	
	public void add(final int numProductos) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "name"));
		Iterable<Producto> productos = repository.findAll(sort);
		Producto first = productos.iterator().next();
		String lastProductoIndex = first.getName().substring("producto".length(),first.getName().length());
		saveProductos(numProductos, Integer.parseInt(lastProductoIndex));
	}
	
	public static void printAllProductosByName(ProductoRepository repository) {
		System.out.println("## Return all productos sorted by name");
		Iterable<Producto> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}

	private void saveProductos(int numProductos, int start) {
		int groups = 1;
		if(numProductos > 100) {
			groups = numProductos/100;
		}
		if(groups > 0) {
			for(int i=start;i<groups+start;i++) {
				repository.saveAll(createProductos(i, numProductos));
			}
		}
	}

	private List<Producto> createProductosOld(int start, int numProductos) {
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
		for (int i = start; i < numProductos + start; i++) {
			productos.add(new Producto(referencia + i , "producto" + i));
		}
	    return productos;
	}
	
	private List<Producto> createProductos(int start, int numProductos) {
		logger.debug("Creando " + numProductos + " productos");
		List<Producto> productos = new ArrayList<Producto>();		
		for (int i = start; i < numProductos; i++) {
			String cempresa = String.format("%03d",Integer.valueOf(i));
			String centrooo = String.format("%04d",Integer.valueOf(i));
			String cdepartm = String.format("%04d",Integer.valueOf(i));
			String cfamilia = String.format("%03d",Integer.valueOf(i));
			String cbarraaa = String.format("%04d",Integer.valueOf(i));
			String ctallaec = String.format("%03d",Integer.valueOf(i));
			String cdivisio = String.format("%02d",Integer.valueOf(i));
			String cniveln = String.format("%01d",Integer.valueOf(i));
			String cfabrica = String.format("%06d",Integer.valueOf(i));
			String cmarmuma = String.format("%014d",Integer.valueOf(i));
			String referencia = cempresa + centrooo + cdepartm + cfamilia + cbarraaa + ctallaec + cdivisio + cniveln + cfabrica + cmarmuma;
			productos.add(new Producto(referencia + i , "producto" + i));
		}
	    return productos;
	}
}
