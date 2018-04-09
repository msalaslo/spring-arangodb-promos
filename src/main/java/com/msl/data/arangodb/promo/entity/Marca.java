package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("marcas")
@HashIndex(fields = { "cmarmuma"}, unique = true)
public class Marca {
	
	@Id
    public String id;
	
	public String cmarmuma;
	
	public Marca(String cmarmuma) {
		super();
		this.cmarmuma = cmarmuma;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCmarmuma() {
		return cmarmuma;
	}

	public void setCmarmuma(String cmarmuma) {
		this.cmarmuma = cmarmuma;
	}

}
