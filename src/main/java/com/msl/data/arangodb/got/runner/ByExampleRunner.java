package com.msl.data.arangodb.got.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.msl.data.arangodb.got.entity.Character;
import com.msl.data.arangodb.got.repository.CharacterRepository;
 
@ComponentScan("com.msl.data.arangodb.got")
public class ByExampleRunner implements CommandLineRunner {
 
  @Autowired
  private CharacterRepository repository;
  
 final Character nedStark = new Character("Ned", "Stark", false, 41);
 
  @Override
  public void run(final String... args) throws Exception {
    System.out.println("# Query by example");
    System.out.println(String.format("## Find character which exactly match %s", nedStark));
    Character foundNedStark = repository.findOne(Example.of(nedStark));
    System.out.println(String.format("Found %s", foundNedStark));
    
    System.out.println("## Find all dead Starks");
    Iterable<Character> allDeadStarks = repository
    .findAll(Example.of(new Character(null, "Stark", false), ExampleMatcher.matchingAll()
    .withMatcher("surname", match -> match.exact()).withIgnorePaths("name", "age")));
    allDeadStarks.forEach(System.out::println);
    
    System.out.println("## Find all Starks which are 30 years younger than Ned Stark");
    
    Iterable<Character> allYoungerStarks = repository.findAll(
      Example.of(foundNedStark, ExampleMatcher.matchingAll().withMatcher("surname", match -> match.exact())
      .withIgnorePaths("id", "name", "alive").withTransformer("age", age -> ((int) age) - 30)));
    allYoungerStarks.forEach(System.out::println);
    
    System.out.println("## Find all character which surname ends with 'ark' (ignore case)");
    Iterable<Character> ark = repository.findAll(Example.of(new Character(null, "ark", false),
      ExampleMatcher.matchingAll().withMatcher("surname", match -> match.endsWith()).withIgnoreCase()
      .withIgnorePaths("name", "alive", "age")));
    ark.forEach(System.out::println);
  }
 
}
