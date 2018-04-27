package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class CentroEmpresa {

	@Id
	private String id;
	
	@From
	private Centro centro;
	
	@To
	private Empresa empresa;
	
	private String name;

	public CentroEmpresa(final Centro centro, final Empresa empresa) {
		super();
		this.centro = centro;
		this.empresa = empresa;
		this.name = "P:" + empresa.getName() + "/M:" + centro.getName();
	}

	@Override
	public String toString() {
		return "Empresa/Centro [id=" + id + ", centro=" + centro.getCentroo() + ", empresa=" + empresa.getCempresa() + "]";
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
