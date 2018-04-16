package com.msl.data.arangodb.promo.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

@Document("marcas")
@HashIndex(fields = { "cmarmuma"}, unique = true)
public class Marca extends EntidadPromocionada{
	
		
	private String cmarmuma;
	
	private String name; 
	
	public Marca(String cmarmuma, String name) {
		super();
		this.cmarmuma = cmarmuma;
		this.name = name;
	}

	public String getCmarmuma() {
		return cmarmuma;
	}

	public void setCmarmuma(String cmarmuma) {
		this.cmarmuma = cmarmuma;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    @Override
    public String toString() {
        return new ToStringBuilder(this).
        		append("id", id).
                append("cmarmuma", cmarmuma).
                append("name", name).
        		toString();
    }
}
