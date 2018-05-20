package com.msl.data.arangodb.promo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msl.data.arangodb.promo.loader.DBLoaderCLRunner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringDataArangoDBApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringDataArangoDBApplication.class);
	public final static String name = "ArangoDB Promo";
			
	public static void main(final String... args) {
		logger.info("Iniciando aplicacion" + name);
		SpringApplication.run(SpringDataArangoDBApplication.class, args);
//		SpringApplication.run(DBLoaderCLRunner.class, args);
//		System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
		logger.info("Aplicacion " + name + " iniciada");
	}
}
