package com.msl.data.arangodb.got.runner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import com.msl.data.arangodb.got.entity.Character;
import com.msl.data.arangodb.got.repository.CharacterRepository;

@ComponentScan("com.arangodb.spring.demo")
public class DerivedMethodRunner implements CommandLineRunner {

	@Autowired
	private CharacterRepository repository;

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("# Derived queries");

		System.out.println("## Find all characters with surname 'Lannister'");
		Iterable<Character> lannisters = repository.findBySurname("Lannister");
		lannisters.forEach(System.out::println);

		System.out.println("## Find top 2 Lannnisters ordered by age");
		Collection<Character> top2 = repository.findTop2DistinctBySurnameIgnoreCaseOrderByAgeDesc("lannister");
		top2.forEach(System.out::println);

		System.out.println(
				"## Find all characters which name is 'Bran' or 'Sansa' and it's surname ends with 'ark' and are between 10 and 16 years old");
		List<Character> youngStarks = repository.findBySurnameEndsWithAndAgeBetweenAndNameInAllIgnoreCase("ark", 10, 16,
				new String[] { "Bran", "Sansa" });
		youngStarks.forEach(System.out::println);

		System.out.println("## Find a single character by name & surname");
		Optional<Character> tyrion = repository.findByNameAndSurname("Tyrion", "Lannister");
		tyrion.ifPresent(c -> {
			System.out.println(String.format("Found %s", c));
		});
		
		System.out.println("## Count how many characters are still alive");
		Integer alive = repository.countByAliveTrue();
		System.out.println(String.format("There are %s characters still alive", alive));
		
//		System.out.println("## Remove all characters except of which surname is 'Stark' and which are still alive");
//		repository.removeBySurnameNotLikeOrAliveFalse("Stark");
//		repository.findAll().forEach(System.out::println);
	}

}