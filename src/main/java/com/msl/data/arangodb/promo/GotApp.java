package com.msl.data.arangodb.promo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msl.data.arangodb.promo.loader.DBLoaderCLRunner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GotApp {
	public static void main(final String... args) {
		Object[] runner = new Object[] {DBLoaderCLRunner.class};

//		System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
		SpringApplication.run(runner, args);

	}
}
