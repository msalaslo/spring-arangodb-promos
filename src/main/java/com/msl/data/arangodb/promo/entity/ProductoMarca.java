package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class ProductoMarca {

	@Id
	private String id;

	@From
	private Producto producto;
	
	@To
	private Marca marca;
	
	private String name;

	public ProductoMarca(final Producto producto, final Marca marca) {
		super();
		this.marca = marca;
		this.producto = producto;
		this.name = "P:" + producto.getName() + "/M:" + marca.getName();
	}

	@Override
	public String toString() {
		return "Producto/Marca [id=" + id + ", marca=" + marca.getCmarmuma() + ", producto=" + producto.getReferencia() + "]";
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
