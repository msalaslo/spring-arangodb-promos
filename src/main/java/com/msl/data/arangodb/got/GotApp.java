package com.msl.data.arangodb.got;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msl.data.arangodb.got.runner.AQLRunner;
import com.msl.data.arangodb.got.runner.ByExampleRunner;
import com.msl.data.arangodb.got.runner.CrudRunner;
import com.msl.data.arangodb.got.runner.DerivedMethodRunner;
import com.msl.data.arangodb.got.runner.GeospatialRunner;
import com.msl.data.arangodb.got.runner.RelationsRunner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GotApp {
	public static void main(final String... args) {
		Object[] runner = new Object[] { CrudRunner.class, ByExampleRunner.class, DerivedMethodRunner.class, RelationsRunner.class, AQLRunner.class, GeospatialRunner.class  };
		System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
	}
}
