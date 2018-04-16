package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class PromocionEntidad {

	@Id
	private String id;

	@From
	private EntidadPromocionada entidad;

	@To
	private Promocion promocion;

	public PromocionEntidad(final Promocion promocion, final EntidadPromocionada entidad) {
		super();
		this.entidad = entidad;
		this.promocion = promocion;
	}

	@Override
	public String toString() {
		return "Entidad promocionada [id=" + id + ", id entidad=" + entidad + ", cod promocion=" + promocion.getCpromoci() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntidadPromocionada getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadPromocionada entidad) {
		this.entidad = entidad;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

}
