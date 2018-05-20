package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("familias")
@HashIndex(fields = { "cfamilia"}, unique = true)
public class Familia implements Promocionable,RelacionableParent{
	
	@Id
    public String id;
	
	public String cfamilia;
	
	public String name;
	
	@Relations(edges = FamiliaPromocion.class, lazy = false)
	public Collection<Promocion> promociones;
	
	public Collection<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(Collection<Promocion> promociones) {
		this.promociones = promociones;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Familia(String cfamilia, String name) {
		super();
		this.cfamilia = cfamilia;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCfamilia() {
		return cfamilia;
	}

	public void setCfamilia(String cfamilia) {
		this.cfamilia = cfamilia;
	}

}
