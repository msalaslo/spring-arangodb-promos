package com.msl.data.arangodb.promo.entity;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class EmpresaPromocion {

	@Id
	private String id;

	@From
	private Empresa empresa;

	@To
	private Promocion promocion;
	
	private String name;

	public EmpresaPromocion(final Empresa empresa, final Promocion promocion) {
		super();
		this.empresa = empresa;
		this.promocion = promocion;
		this.name = "M:" + empresa.getName() + "/P:" + promocion.getName();
	}

	@Override
	public String toString() {
		return "Empresa promocionada [id=" + id + ", empresa=" + empresa.getCempresa() + ", promocion=" + promocion.getCpromoci() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
