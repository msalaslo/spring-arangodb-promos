package com.msl.data.arangodb.got.runner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

import com.arangodb.springframework.core.ArangoOperations;
import com.msl.data.arangodb.got.entity.Character;
import com.msl.data.arangodb.got.repository.CharacterRepository;

@ComponentScan("com.msl.data.arangodb.got")
public class CrudRunner implements CommandLineRunner {

	@Autowired
	private ArangoOperations operations;
	@Autowired
	private CharacterRepository repository;

	@Override
	public void run(final String... args) throws Exception {
	    // first drop the database so that we can run this multiple times with the same dataset
	    operations.dropDatabase();
	 
	    // save a single entity in the database
	    // there is no need of creating the collection first. This happen automatically
	    final Character nedStark = new Character("Ned", "Stark", true, 41);
	    repository.save(nedStark);
	    // the generated id from the database is set in the original entity
	    System.out.println(String.format("Ned Stark saved in the database with id: '%s'", nedStark.getId()));
	 
	    // lets take a look whether we can find Ned Stark in the database
	    final Character foundNed = repository.findOne(nedStark.getId());
	    System.out.println(String.format("Found %s", foundNed));
	    
	    nedStark.setAlive(false);
	    repository.save(nedStark);
	    final Character deadNed = repository.findOne(nedStark.getId());
	    System.out.println(String.format("The 'alive' flag of the persisted Ned Stark is now '%s'", deadNed.isAlive()));
	    
	    Collection<Character> createCharacters = createCharacters();
	    System.out.println(String.format("Save %s additional chracters", createCharacters.size()));
	    repository.save(createCharacters);
	     
	    Iterable<Character> all = repository.findAll();
	    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
	    System.out.println(String.format("A total of %s characters are persisted in the database", count));
	    
	    printAllCharsByName(repository);
	}
	
	public static void printAllCharsByName(CharacterRepository repository) {
		System.out.println("## Return all characters sorted by name");
		Iterable<Character> allSorted = repository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "name")));
		allSorted.forEach(System.out::println);
	}
	
	public static Collection<Character> createCharacters() {
	    return Arrays.asList(new Character("Ned", "Stark", false, 41), new Character("Robert", "Baratheon", false),
	      new Character("Jaime", "Lannister", true, 36), new Character("Catelyn", "Stark", false, 40),
	      new Character("Cersei", "Lannister", true, 36), new Character("Daenerys", "Targaryen", true, 16),
	      new Character("Jorah", "Mormont", false), new Character("Petyr", "Baelish", false),
	      new Character("Viserys", "Targaryen", false), new Character("Jon", "Snow", true, 16),
	      new Character("Sansa", "Stark", true, 13), new Character("Arya", "Stark", true, 11),
	      new Character("Robb", "Stark", false), new Character("Theon", "Greyjoy", true, 16),
	      new Character("Bran", "Stark", true, 10), new Character("Joffrey", "Baratheon", false, 19),
	      new Character("Sandor", "Clegane", true), new Character("Tyrion", "Lannister", true, 32),
	      new Character("Khal", "Drogo", false), new Character("Tywin", "Lannister", false),
	      new Character("Davos", "Seaworth", true, 49), new Character("Samwell", "Tarly", true, 17),
	      new Character("Stannis", "Baratheon", false), new Character("Melisandre", null, true),
	      new Character("Margaery", "Tyrell", false), new Character("Jeor", "Mormont", false),
	      new Character("Bronn", null, true), new Character("Varys", null, true), new Character("Shae", null, false),
	      new Character("Talisa", "Maegyr", false), new Character("Gendry", null, false),
	      new Character("Ygritte", null, false), new Character("Tormund", "Giantsbane", true),
	      new Character("Gilly", null, true), new Character("Brienne", "Tarth", true, 32),
	      new Character("Ramsay", "Bolton", true), new Character("Ellaria", "Sand", true),
	      new Character("Daario", "Naharis", true), new Character("Missandei", null, true),
	      new Character("Tommen", "Baratheon", true), new Character("Jaqen", "H'ghar", true),
	      new Character("Roose", "Bolton", true), new Character("The High Sparrow", null, true));
	  }
}
