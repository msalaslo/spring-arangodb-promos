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

import com.msl.data.arangodb.promo.entity.Promocion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromocionRepositoryTests {
	
	public static final int NUM_PROMOS = 10000;

	@Autowired
	PromocionRepository repository;

	String cempresa = "123";
	String centrooo = "1234";
	String canlvnta = "U";
	String codpromoci = "12345678";
	String cdepartm = "1234";
	String cfamilia = "123";
	String cbarraaa = "1234";
	String ctallaec = "123";
	String dticprom = "12";
	String cdivisio = "12";
	String cniveln = "1";
	String xexcluye = "1";
	String cfabrica = "123456";
	String cmarmuma = "12345678901234";

	String finiefec = "20170101";
	String ffinefec = "20180101";
	String choraini = "0800";
	String chorafin = "0000";
	String cemprvnt = "001";
	String ccentvnt = "0001";
	String despromo = "Descripcion promocion";
	String ccarpeta = "01234567890123";
	String descarpe = "Descripcion carpeta";
	String coorigen = "123";
	String codplaex = "123456789012345678901234";
	String chordiad = "123";
	String chordiah = "456";
	String xtipobon = "B";
	String xusopweb = "W";

	@Before
	public void setUp() {
		repository.deleteAll();
		repository.save(new Promocion(
						codpromoci,
						canlvnta,
						dticprom, 
						xexcluye, 
						finiefec, 
						ffinefec, 
						choraini, 
						chorafin, 
						cemprvnt, 
						ccentvnt, 
						despromo,
						ccarpeta, 
						descarpe, 
						coorigen, 
						codplaex, 
						chordiad, 
						chordiah, 
						xtipobon, 
						xusopweb
				));
	}


	@Test
	public void findByCodpromoci() {
		Promocion result = repository.findByCodpromoci(codpromoci);
		assertThat(result).extracting("codpromoci").contains(codpromoci);
	}

	@Test
	public void checkSize() {
		Iterable<Promocion> result = repository.findAll();
		int size = 0;
		for(Promocion value : result) {
		   size++;
		}
		System.out.println("Product repository size:" + size);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void createPromociones() {
		Collection<Promocion> result = createPromociones(NUM_PROMOS);
		System.out.println(String.format("Save %s additional promos", NUM_PROMOS));
		repository.saveAll(result);
		assertThat(result).extracting("codpromoci").contains(codpromoci);
	}
	
	public List<Promocion> createPromociones(int numPromociones) {
		List<Promocion> promociones = new ArrayList<Promocion>();

		String canlvnta = "CN";
		String codpromoci = "CODPROMO";
		String dticprom = "TP";
		String xexcluye = "E";
		String finiefec = "20170101";
		String ffinefec = "20180101";
		String choraini = "0800";
		String chorafin = "0000";
		String cemprvnt = "001";
		String ccentvnt = "0001";
		String despromo = "Descripcion promocion";
		String ccarpeta = "01234567890123";
		String descarpe = "Descripcion carpeta";
		String coorigen = "123";
		String codplaex = "123456789012345678901234";
		String chordiad = "123";
		String chordiah = "456";
		String xtipobon = "B";
		String xusopweb = "W";

		for (int codPromocion = 0; codPromocion < numPromociones; codPromocion++) {
			Promocion promocion = new Promocion(codpromoci + codPromocion, canlvnta, dticprom, xexcluye, finiefec,
					ffinefec, choraini, chorafin, cemprvnt, ccentvnt, despromo, ccarpeta, descarpe, coorigen, codplaex,
					chordiad, chordiah, xtipobon, xusopweb);
			promociones.add(promocion);
		}
		return promociones;
	}
}