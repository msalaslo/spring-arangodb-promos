package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("centros")
@HashIndex(fields = { "centroo"}, unique = true)
public class Centro implements Promocionable, Relacionable, RelacionableParent{
	
	@Id
    public String id;
		
	public String centroo;
	
	public String name;
	
	@Relations(edges = EmpresaPromocion.class, lazy = true)
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
	
	public Centro(String centroo, String name) {
		super();
		this.centroo = centroo;
		this.name = name;
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
