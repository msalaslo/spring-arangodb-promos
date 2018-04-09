package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("familias")
@HashIndex(fields = { "cfamilia"}, unique = true)
public class Familia {
	
	@Id
    public String id;
	
	
	public String cfamilia;
	
	public Familia(String cfamilia) {
		super();
		this.cfamilia = cfamilia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCfamilia() {
		return cfamilia;
	}

	public void setCfamilia(String cfamilia) {
		this.cfamilia = cfamilia;
	}

}
