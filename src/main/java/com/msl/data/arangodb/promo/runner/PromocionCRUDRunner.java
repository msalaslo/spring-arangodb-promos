package com.msl.data.arangodb.promo.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

import com.arangodb.springframework.core.ArangoOperations;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.PromocionRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class PromocionCRUDRunner implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(PromocionCRUDRunner.class);

	@Autowired
	private ArangoOperations operations;
	@Autowired
	private PromocionRepository repository;

	String canlvnta = "U";
	String codpromoci = "12345678";
	String dticprom = "12";
	String xexcluye = "1";
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

	@Override
	public void run(final String... args) throws Exception {
		// first drop the database so that we can run this multiple times with the same dataset
//		operations.dropDatabase();

		// save a single entity in the database
		// there is no need of creating the collection first. This happen automatically
//		final Promocion promo1 = new Promocion(codpromoci, canlvnta, dticprom, xexcluye, finiefec, ffinefec, choraini,
//				chorafin, cemprvnt, ccentvnt, despromo, ccarpeta, descarpe, coorigen, codplaex, chordiad, chordiah,
//				xtipobon, xusopweb);
//		repository.save(promo1);
//		// the generated id from the database is set in the original entity
//		System.out.println(String.format("Promo1 saved in the database with id: '%s'", promo1.getId()));
//
//		// lets take a look whether we can find promo1 in the database
//		final Promocion foundNed = repository.findOne(promo1.getId());
//		System.out.println(String.format("Found %s", foundNed));
//
//		promo1.setDticprom("TP");
//		repository.save(promo1);
//		final Promocion deadNed = repository.findOne(promo1.getId());
//		System.out.println(
//				String.format("The 'Dticprom' flag of the persisted Promo1 is now '%s'", deadNed.getDticprom()));

//		int numpromos = 1000;
//		Iterable<Promocion> createPromos = createPromos(numpromos);
//		System.out.println(String.format("Save %s additional promos", numpromos));
//		repository.save(createPromos);

		Iterable<Promocion> all = repository.findAll();
		long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
		System.out.println(String.format("A total of %s promos are persisted in the database", count));

//		printAllPromosByName(repository);
	}

	public static void printAllPromosByName(PromocionRepository repository) {
		System.out.println("## Return all characters sorted by name");
		Iterable<Promocion> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}

	private List<Promocion> createPromos(int numPromociones) {
		List<Promocion> promociones = new ArrayList<Promocion>();

		String canlvnta = "CN";
		String codpromoci = "CODPROM";
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
