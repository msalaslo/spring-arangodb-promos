package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Relations;

public abstract class EntidadPromocionada {
	
	@Id
	public String id;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Relations(edges = ProductoPromocion.class, lazy = true)
	public Collection<Promocion> promociones;
	
	public Collection<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(Collection<Promocion> promociones) {
		this.promociones = promociones;
	}

}
