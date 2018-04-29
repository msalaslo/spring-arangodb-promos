package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class CentroPromocion {

	@Id
	private String id;

	@From
	private Centro centro;

	@To
	private Promocion promocion;
	
	private String name;

	public CentroPromocion(final Centro centro, final Promocion promocion) {
		super();
		this.centro = centro;
		this.promocion = promocion;
		this.name = "M:" + centro.getName() + "/P:" + promocion.getName();
	}

	@Override
	public String toString() {
		return "Centro promocionada [id=" + id + ", centro=" + centro.getCentroo() + ", promocion=" + promocion.getCpromoci() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
