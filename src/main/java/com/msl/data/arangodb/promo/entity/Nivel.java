package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("niveles")
@HashIndex(fields = { "cniveln"}, unique = true)
public class Nivel {
	
	@Id
    public String id;
	
	public String cniveln;
	
	public Nivel(String cniveln) {
		super();
		this.cniveln = cniveln;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCniveln() {
		return cniveln;
	}

	public void setCniveln(String cniveln) {
		this.cniveln = cniveln;
	}

}
