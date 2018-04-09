package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("tallas")
@HashIndex(fields = { "ctallaec"}, unique = true)
public class Talla {
	
	@Id
    public String id;
	
	public String ctallaec;
	
	public Talla(String ctallaec) {
		super();
		this.ctallaec = ctallaec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCtallaec() {
		return ctallaec;
	}

	public void setCtallaec(String ctallaec) {
		this.ctallaec = ctallaec;
	}

}
