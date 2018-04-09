package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("divisiones")
@HashIndex(fields = { "cdivisio"}, unique = true)
public class Division {
	
	@Id
    public String id;
	
	public String cdivisio;
	
	public Division(String cdivisio) {
		super();
		this.cdivisio = cdivisio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCdivisio() {
		return cdivisio;
	}

	public void setCdivisio(String cdivisio) {
		this.cdivisio = cdivisio;
	}

}
