package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class FamiliaPromocion {

	@Id
	private String id;

	@From
	private Familia familia;

	@To
	private Promocion promocion;
	
	private String name;

	public FamiliaPromocion(final Familia familia, final Promocion promocion) {
		super();
		this.familia = familia;
		this.promocion = promocion;
		this.name = "M:" + familia.getName() + "/P:" + promocion.getName();
	}

	@Override
	public String toString() {
		return "Familia promocionada [id=" + id + ", familia=" + familia.getCfamilia() + ", promocion=" + promocion.getCpromoci() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
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
