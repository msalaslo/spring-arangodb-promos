package com.msl.data.arangodb.promo.loader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductoLoaderTest {
	
	@Autowired
	ProductoLoader loader;
	
	@Before
	public void setUp() {
		loader.deleteAll();
	}

	@Test
	public void createProductos() {
		loader.load();
	}
}