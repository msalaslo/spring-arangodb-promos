package com.msl.data.arangodb.promo.loader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromocionLoaderTest {

	public static final int NUM_PROMOCIONES = 10;
	
	@Autowired
	PromocionLoader loader;
	
	@Before
	public void setUp() {
		loader.deleteAll();
	}

	@Test
	public void createPromociones() {
		loader.load(NUM_PROMOCIONES);
	}
}