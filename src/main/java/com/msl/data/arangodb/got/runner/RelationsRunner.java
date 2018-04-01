package com.msl.data.arangodb.got.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.msl.data.arangodb.got.entity.Character;
import com.msl.data.arangodb.got.entity.ChildOf;
import com.msl.data.arangodb.got.repository.CharacterRepository;
import com.msl.data.arangodb.got.repository.ChildOfRepository;

public class RelationsRunner implements CommandLineRunner {

	@Autowired
	private CharacterRepository characterRepo;
	@Autowired
	private ChildOfRepository childOfRepo;

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("# Relations");
		characterRepo.save(CrudRunner.createCharacters());

		// first create some relations for the Starks and Lannisters
		characterRepo.findByNameAndSurname("Ned", "Stark").ifPresent(ned -> {
			characterRepo.findByNameAndSurname("Catelyn", "Stark").ifPresent(catelyn -> {
				characterRepo.findByNameAndSurname("Robb", "Stark").ifPresent(robb -> {
					childOfRepo.save(Arrays.asList(new ChildOf(robb, ned), new ChildOf(robb, catelyn)));
				});
				characterRepo.findByNameAndSurname("Sansa", "Stark").ifPresent(sansa -> {
					childOfRepo.save(Arrays.asList(new ChildOf(sansa, ned), new ChildOf(sansa, catelyn)));
				});
				characterRepo.findByNameAndSurname("Arya", "Stark").ifPresent(arya -> {
					childOfRepo.save(Arrays.asList(new ChildOf(arya, ned), new ChildOf(arya, catelyn)));
				});
				characterRepo.findByNameAndSurname("Bran", "Stark").ifPresent(bran -> {
					childOfRepo.save(Arrays.asList(new ChildOf(bran, ned), new ChildOf(bran, catelyn)));
				});
			});
			characterRepo.findByNameAndSurname("Jon", "Snow")
					.ifPresent(bran -> childOfRepo.save(new ChildOf(bran, ned)));
		});

		characterRepo.findByNameAndSurname("Tywin", "Lannister").ifPresent(tywin -> {
			characterRepo.findByNameAndSurname("Jaime", "Lannister").ifPresent(jaime -> {
				childOfRepo.save(new ChildOf(jaime, tywin));
				characterRepo.findByNameAndSurname("Cersei", "Lannister").ifPresent(cersei -> {
					childOfRepo.save(new ChildOf(cersei, tywin));
					characterRepo.findByNameAndSurname("Joffrey", "Baratheon").ifPresent(joffrey -> {
						childOfRepo.save(Arrays.asList(new ChildOf(joffrey, jaime), new ChildOf(joffrey, cersei)));
					});
				});
			});
			characterRepo.findByNameAndSurname("Tyrion", "Lannister")
					.ifPresent(tyrion -> childOfRepo.save(new ChildOf(tyrion, tywin)));
		});

		characterRepo.findByNameAndSurname("Ned", "Stark").ifPresent(nedStark -> {
			System.out.println(String.format("## These are the children of %s:", nedStark));
			nedStark.getChilds().forEach(System.out::println);
		});

		System.out.println("## These are the parents of 'Sansa'");
		Iterable<Character> parentsOfSansa = characterRepo.findByChildsName("Sansa");
		parentsOfSansa.forEach(System.out::println);

		System.out.println("## These parents have a child which is between 16 and 20 years old");
		Iterable<Character> childsBetween16a20 = characterRepo.findByChildsAgeBetween(16, 20);
		childsBetween16a20.forEach(System.out::println);
	}

}
