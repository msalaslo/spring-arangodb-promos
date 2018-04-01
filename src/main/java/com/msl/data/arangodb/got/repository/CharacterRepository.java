package com.msl.data.arangodb.got.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.BindVars;
import com.arangodb.springframework.annotation.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.annotation.QueryOptions;
import com.arangodb.springframework.repository.ArangoRepository;
import com.msl.data.arangodb.got.entity.Character;
 
public interface CharacterRepository extends ArangoRepository<Character> {
	
	Iterable<Character> findBySurname(String surname);
	Collection<Character> findTop2DistinctBySurnameIgnoreCaseOrderByAgeDesc(String surname);
	 
	List<Character> findBySurnameEndsWithAndAgeBetweenAndNameInAllIgnoreCase(
	  String suffix,
	  int lowerBound,
	  int upperBound,
	  String[] nameList);
	
	Optional<Character> findByNameAndSurname(String name, String surname);
	
	Integer countByAliveTrue();
	
	void removeBySurnameNotLikeOrAliveFalse(String surname);
	
	Iterable<Character> findByChildsName(String name);
	 
	Iterable<Character> findByChildsAgeBetween(int lowerBound, int upperBound);
	
	@Query("FOR c IN characters FILTER c.age > @0 SORT c.age DESC RETURN c")
	Iterable<Character> getOlderThan(int value);
	
	@Query("FOR c IN characters FILTER c.surname == @surname SORT c.age ASC RETURN c")
	Iterable<Character> getWithSurname(@Param("surname") String value);
	
	@Query("FOR c IN @@col FILTER c.surname == @surname AND c.age > @age RETURN c")
	Iterable<Character> getWithSurnameOlderThan(@Param("age") int value, @BindVars Map<String, Object> bindvars);
	
	@Query("FOR c IN @@col FILTER c.surname == @surname AND c.age > @age RETURN c")
	@QueryOptions(count = true)
	ArangoCursor<Character> getWithSurnameOlderThanQueryOptions(@Param("age") int value, @BindVars Map<String, Object> bindvars);
	
	@Query("FOR v IN 1..2 INBOUND @id @@edgeCol SORT v.age DESC RETURN DISTINCT v")
	Set<Character> getAllChildsAndGrandchilds(@Param("id") String id, @Param("@edgeCol") Class<?> edgeCollection);
}