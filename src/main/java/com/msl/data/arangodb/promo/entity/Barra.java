package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("barras")
@HashIndex(fields = { "cbarraaa"}, unique = true)
public class Barra {
	
	@Id
    public String id;
	
	public String cbarraaa;
	
	public Barra(String cbarraaa) {
		super();
		this.cbarraaa = cbarraaa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCbarraaa() {
		return cbarraaa;
	}

	public void setCbarraaa(String cbarraaa) {
		this.cbarraaa = cbarraaa;
	}

}
