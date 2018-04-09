package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("fabricas")
@HashIndex(fields = { "cfabrica"}, unique = true)
public class Fabrica {
	
	@Id
    public String id;
	
	public String cfabrica;
	
	public Fabrica(String cfabrica) {
		super();
		this.cfabrica = cfabrica;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCfabrica() {
		return cfabrica;
	}

	public void setCfabrica(String cfabrica) {
		this.cfabrica = cfabrica;
	}

}
