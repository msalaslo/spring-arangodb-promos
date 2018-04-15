package com.msl.data.arangodb.promo.loader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductoPromocionRelationLoaderTest {

	public static final int NUM_PRODS = 100000;
	
	@Autowired
	ProductoPromocionRelationsLoader loader;

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

	}

	@Test
	public void loadRealtions() {
		loader.load();
	}




}