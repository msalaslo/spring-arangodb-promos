package com.msl.data.arangodb.got.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("characters")
@HashIndex(fields = { "name", "surname" }, unique = true)
public class Character {

	@Id
	private String id;

	private String name;
	private String surname;
	private boolean alive;
	private Integer age;
	@Relations(edges = ChildOf.class, lazy = true)
	private Collection<Character> childs;

	public Character() {
		super();
	}

	public Character(final String name, final String surname, final boolean alive) {
		super();
		this.name = name;
		this.surname = surname;
		this.alive = alive;
	}

	public Character(final String name, final String surname, final boolean alive, final Integer age) {
		super();
		this.name = name;
		this.surname = surname;
		this.alive = alive;
		this.age = age;
	}

	public Collection<Character> getChilds() {
		return childs;
	}

	public void setChilds(Collection<Character> childs) {
		this.childs = childs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", surname=" + surname + ", alive=" + alive + ", age=" + age
				+ "]";
	}

}