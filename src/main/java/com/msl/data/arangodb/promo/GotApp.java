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
		SpringApplication.run(GotApp.class, args);
//		SpringApplication.run(DBLoaderCLRunner.class, args);
//		System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
	}
}
