package com.msl.data.arangodb.promo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msl.data.arangodb.promo.runner.ProductoCRUDRunner;
import com.msl.data.arangodb.promo.runner.PromocionCRUDRunner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GotApp {
	public static void main(final String... args) {
		Object[] runner = new Object[] { ProductoCRUDRunner.class, PromocionCRUDRunner.class};

//		Object[] runner = new Object[] { CrudRunner.class, ByExampleRunner.class, DerivedMethodRunner.class, RelationsRunner.class, AQLRunner.class, GeospatialRunner.class  };
//		System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
		SpringApplication.run(runner, args);

	}
}
