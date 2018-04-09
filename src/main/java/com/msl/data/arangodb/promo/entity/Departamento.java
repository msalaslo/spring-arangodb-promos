package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("departamentos")
@HashIndex(fields = { "cdepartm"}, unique = true)
public class Departamento {
	
	@Id
    public String id;
	
	
	public String cdepartm;
	
	public Departamento(String cdepartm) {
		super();
		this.cdepartm = cdepartm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCdepartm() {
		return cdepartm;
	}

	public void setCdepartm(String cdepartm) {
		this.cdepartm = cdepartm;
	}





}
