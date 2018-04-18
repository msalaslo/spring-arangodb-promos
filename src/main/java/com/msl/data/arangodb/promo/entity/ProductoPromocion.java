package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class ProductoPromocion {

	@Id
	private String id;

	@From
	private Producto producto;

	@To
	private Promocion promocion;
	
	private String name;

	public ProductoPromocion(final Producto producto, final Promocion promocion) {
		super();
		this.producto = producto;
		this.promocion = promocion;
		this.name = "P:" + producto.getName() + "/P:" + promocion.getName();
	}

	@Override
	public String toString() {
		return "Producto promocionado [id=" + id + ", producto=" + producto + ", promocion=" + promocion + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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
