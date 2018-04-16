package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class MarcaPromocion {

	@Id
	private String id;

	@From
	private Marca marca;

	@To
	private Promocion promocion;

	public MarcaPromocion(final Marca marca, final Promocion promocion) {
		super();
		this.marca = marca;
		this.promocion = promocion;
	}

	@Override
	public String toString() {
		return "Marca promocionada [id=" + id + ", marca=" + marca.getCmarmuma() + ", promocion=" + promocion.getCpromoci() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

}
