package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("centros")
@HashIndex(fields = { "centroo"}, unique = true)
public class Centro {
	
	@Id
    public String id;
	
	
	public String centroo;
	
	public Centro(String centroo) {
		super();
		this.centroo = centroo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCentroo() {
		return centroo;
	}

	public void setCentroo(String centroo) {
		this.centroo = centroo;
	}



}
