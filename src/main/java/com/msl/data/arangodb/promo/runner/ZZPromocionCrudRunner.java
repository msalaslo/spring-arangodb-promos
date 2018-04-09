package com.msl.data.arangodb.promo.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import com.arangodb.springframework.core.ArangoOperations;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.PromocionRepository;

@ComponentScan("com.msl.data.arangodb.promo")
public class ZZPromocionCrudRunner implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(ZZPromocionCrudRunner.class);


	@Autowired
	private ArangoOperations operations;
	@Autowired
	private PromocionRepository repository;
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

	@Override
	public void run(final String... args) throws Exception {
	    // first drop the database so that we can run this multiple times with the same dataset
	    operations.dropDatabase();
	 
	    // save a single entity in the database
	    // there is no need of creating the collection first. This happen automatically
	    final Promocion promo1 = new Promocion(
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
		);
	    repository.save(promo1);
	    // the generated id from the database is set in the original entity
	    System.out.println(String.format("Promo1 saved in the database with id: '%s'", promo1.getId()));
	 
	    // lets take a look whether we can find promo1 in the database
	    final Promocion foundNed = repository.findOne(promo1.getId());
	    System.out.println(String.format("Found %s", foundNed));
	    
	    promo1.setDticprom("TP");
	    repository.save(promo1);
	    final Promocion deadNed = repository.findOne(promo1.getId());
	    System.out.println(String.format("The 'Dticprom' flag of the persisted Promo1 is now '%s'", deadNed.getDticprom()));
	    
	    Iterable<Promocion> createPromos = createPromos();
	    System.out.println(String.format("Save %s additional promos"));
	    repository.save(createPromos);
	     
	    Iterable<Promocion> all = repository.findAll();
	    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
	    System.out.println(String.format("A total of %s promos are persisted in the database", count));
	    
	    printAllPromosByName(repository);
	}
	
	public static void printAllPromosByName(PromocionRepository repository) {
		System.out.println("## Return all characters sorted by name");
		Iterable<Promocion> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}
	
    @Test
    public Iterable<Promocion> createPromos() {
        return repository.save(createPromos(0));
    }
    
    @Async
    public List<Promocion> createPromos(int start){
    	List<Promocion> promociones = new ArrayList<Promocion>();
        int numEmpresas = start + 1;
        int numCentros = start + 1; 
        int numDepartamentos = start + 2; 
        int numDivisiones = start + 2;
        int numFamilias = start + 5;
        int numBarras = start + 5;
        int numTallas = start + 5;
        int numNiveles = start + 5;
        int numMarcas = start + 2;
        int numFabricantes = start + 2;
        int numPromociones = start + 2;
        
        String canlvnta = "CN";
        String codpromoci = "CODPROM";
        String dticprom = "TP";
        String xexcluye = "E";
		String finiefec =  "20170101";
		String ffinefec =  "20180101";
		String choraini =  "0800";
		String chorafin =  "0000";
		String cemprvnt =  "001";
		String ccentvnt =  "0001";
		String despromo =  "Descripcion promocion";
		String ccarpeta =  "01234567890123";
		String descarpe =  "Descripcion carpeta";
		String coorigen =  "123";
		String codplaex =  "123456789012345678901234";
		String chordiad =  "123";
		String chordiah =  "456";
		String xtipobon =  "B";
		String xusopweb =  "W";
		
        int total = numEmpresas * numCentros * numDepartamentos * numDivisiones * numFamilias * numBarras * numTallas * numPromociones;
        logger.debug("Total registros a cargar:" + total);
        for (int cempresa = start; cempresa < numEmpresas; cempresa++) {
        	String cempresaStr = String.format("%03d",Integer.valueOf(cempresa));
        	for (int centrooo = start; centrooo < numCentros; centrooo++) {
        		String centrooStr = String.format("%04d",Integer.valueOf(centrooo));
        		for (int cdivisio = start; cdivisio < numDivisiones; cdivisio++) {
        			String cdivisioStr = String.format("%02d",Integer.valueOf(cdivisio));
        			for (int cdepartm = start; cdepartm < numDepartamentos; cdepartm++) {
        				String cdepartmStr = String.format("%04d",Integer.valueOf(cdepartm));
		        		for (int cfamilia = start; cfamilia < numFamilias; cfamilia++) {
		        			String cfamiliaStr = String.format("%03d",Integer.valueOf(cfamilia));
							for (int cbarraaa = start; cbarraaa < numBarras; cbarraaa++) {
								String cbarraaaStr = String.format("%05d",Integer.valueOf(cbarraaa));
								for (int ctallaec = start; ctallaec < numTallas; ctallaec++) {
									String ctallaecStr = String.format("%03d",Integer.valueOf(ctallaec));
									for(int codPromocion = start; codPromocion < numPromociones; codPromocion++){
										for(int cniveln = start; cniveln < numNiveles; cniveln++){
											String cnivelnStr = String.format("%01d",Integer.valueOf(cniveln));
											for(int cmarmuma = start; cmarmuma < numMarcas; cmarmuma++){
												String cmarmumaStr = String.format("%014d",Integer.valueOf(cmarmuma));
												for(int cfabrica = start; cfabrica < numFabricantes; cfabrica++){
													String cfabricaStr = String.format("%05d",Integer.valueOf(cfabrica));
													Promocion promocion = new Promocion(codpromoci + codPromocion, canlvnta, dticprom, xexcluye, finiefec,
															ffinefec, choraini, chorafin, cemprvnt, ccentvnt, despromo, ccarpeta, descarpe, coorigen, codplaex,
															chordiad, chordiah, xtipobon, xusopweb);
													promociones.add(promocion);
												}
											}
										}
									}
								}
							}
						}
        			}
        		}
			}
        	logger.debug("Centros cargados:" + numCentros);
		}
        logger.debug("Empresa cargadas:" + numEmpresas);
        return promociones;
    }
}
