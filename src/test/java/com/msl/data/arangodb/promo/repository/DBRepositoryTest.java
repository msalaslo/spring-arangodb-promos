package com.msl.data.arangodb.promo.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBRepositoryTest {
	
	@Autowired
	DBRepository repository;

	@Before
	public void setUp() {

	}

	@Test
	public void dropDataBase() {
		repository.dropDatabase();
	}

}
