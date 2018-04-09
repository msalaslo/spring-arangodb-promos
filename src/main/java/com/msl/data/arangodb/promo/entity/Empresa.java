package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("empresas")
@HashIndex(fields = { "cempresa"}, unique = true)
public class Empresa {
	
	@Id
    public String id;
	
	
	public String cempresa;
	
	public Empresa(String cempresa) {
		super();
		this.cempresa = cempresa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCempresa() {
		return cempresa;
	}

	public void setCempresa(String cempresa) {
		this.cempresa = cempresa;
	}

}
