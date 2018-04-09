package com.msl.data.arangodb.promo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.msl.data.arangodb.promo.entity.Producto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductoRepositoryTests {

	public static final int NUM_PRODS = 10000;
	
	@Autowired
	ProductoRepository repository;

	String cempresa = "999";
	String centrooo = "9999";
	String cdepartm = "1234";
	String cfamilia = "123";
	String cbarraaa = "1234";
	String ctallaec = "123";
	String cdivisio = "12";
	String cniveln = "1";
	String cfabrica = "123456";
	String cmarmuma = "12345678901234";
	String referencia = cempresa + centrooo + cdepartm + cfamilia + cbarraaa + ctallaec + cdivisio + cniveln + cfabrica
			+ cmarmuma;

	@Before
	public void setUp() {
		repository.deleteAll();
		repository.save(new Producto(referencia, "setup"));
	}

	@Test
	public void findByReferencia() {
		Iterable<Producto> result = repository.findByReferencia(referencia);
		assertThat(result).extracting("referencia").contains(referencia);
	}

	@Test
	public void createProductos() {
		Collection<Producto> result = createProductos(NUM_PRODS);
	    System.out.println(String.format("Save %s additional products", result.size()));
	    repository.save(result);
		assertThat(result).extracting("referencia").contains(referencia);
	}
	
	@Test
	public void checkSize() {
		List<Producto> result = repository.findAll();
		System.out.println("Product repository size:" + result.size());
		assertThat(result).isNotNull();
	}

	public static Collection<Producto> createProductos(int numProductos) {
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
		String referencia = cempresa + centrooo + cdepartm + cfamilia + cbarraaa + ctallaec + cdivisio + cniveln
				+ cfabrica + cmarmuma;
		List<Producto> productos = new ArrayList<Producto>();
		for (int i = 0; i < numProductos; i++) {
			productos.add(new Producto(referencia + i, "producto" + i));
		}
		return productos;
	}
}