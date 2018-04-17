package com.msl.data.arangodb.promo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arangodb.springframework.core.ArangoOperations;

@Component
public class DBRepository {
	
	@Autowired
	private ArangoOperations operations;
	
	public void dropDatabase() {
		operations.dropDatabase();
	}

}
