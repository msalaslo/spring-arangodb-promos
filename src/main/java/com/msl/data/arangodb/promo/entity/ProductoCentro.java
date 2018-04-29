package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class ProductoCentro {

	@Id
	private String id;

	@From
	private Producto producto;
	
	@To
	private Centro centro;
	
	private String name;

	public ProductoCentro(final Producto producto, final Centro centro) {
		super();
		this.centro = centro;
		this.producto = producto;
		this.name = "P:" + producto.getName() + "/M:" + centro.getName();
	}

	@Override
	public String toString() {
		return "Producto/Centro [id=" + id + ", centro=" + centro.getCentroo() + ", producto=" + producto.getReferencia() + "]";
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
