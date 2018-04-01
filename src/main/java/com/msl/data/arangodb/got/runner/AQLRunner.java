package com.msl.data.arangodb.got.runner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.arangodb.ArangoCursor;
import com.msl.data.arangodb.got.entity.Character;
import com.msl.data.arangodb.got.entity.ChildOf;
import com.msl.data.arangodb.got.repository.CharacterRepository;

public class AQLRunner implements CommandLineRunner {

	@Autowired
	private CharacterRepository repository;

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("# AQL queries");

		System.out.println("## Find all characters which are older than 21 (sort descending)");
		final Iterable<Character> older = repository.getOlderThan(21);
		older.forEach(System.out::println);

		System.out.println("## Find all characters with surname 'Lannister' (sort by age ascending)");
		Iterable<Character> lannisters = repository.getWithSurname("Lannister");
		lannisters.forEach(System.out::println);

		System.out.println("## Find all characters with surname 'Lanister' which are older than 35");
		Map<String, Object> bindvars = new HashMap<>();
		bindvars.put("surname", "Lannister");
		bindvars.put("@col", Character.class);
		Iterable<Character> oldLannisters = repository.getWithSurnameOlderThan(35, bindvars);
		oldLannisters.forEach(System.out::println);
		
		System.out.println("## Find all characters with surname 'Lanister' which are older than 35 with Query Options");
		ArangoCursor<Character> oldLannistersCursor = repository.getWithSurnameOlderThanQueryOptions(35, bindvars);
		System.out.println(String.format("Found %s documents", oldLannistersCursor.getCount()));

		System.out.println("## Find all childs and grantchilds of 'Tywin Lannister' (sort by age descending)");
		repository.findByNameAndSurname("Tywin", "Lannister").ifPresent(tywin -> {
		Set<Character> childs = repository.getAllChildsAndGrandchilds(tywin.getId(), ChildOf.class);
		  childs.forEach(System.out::println);
		});
	}
}
