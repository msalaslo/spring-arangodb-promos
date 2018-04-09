package com.msl.data.arangodb.promo;

import org.springframework.context.annotation.Configuration;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.msl.data.arangodb.promo" })
public class GotConfiguration extends AbstractArangoConfiguration {
	@Override
	public Builder arango() {
		return new ArangoDB.Builder().host("localhost", 8529).user("root").password("admin");
	}

	@Override
	public String database() {
		return "promo";
	}

}
