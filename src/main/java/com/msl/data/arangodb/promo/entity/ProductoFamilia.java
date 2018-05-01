package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class ProductoFamilia {

	@Id
	private String id;

	@From
	private Producto producto;
	
	@To
	private Familia familia;
	
	private String name;

	public ProductoFamilia(final Producto producto, final Familia familia) {
		super();
		this.familia = familia;
		this.producto = producto;
		this.name = "P:" + producto.getName() + "/M:" + familia.getName();
	}

	@Override
	public String toString() {
		return "Producto/Familia [id=" + id + ", familia=" + familia.getCfamilia() + ", producto=" + producto.getReferencia() + "]";
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
