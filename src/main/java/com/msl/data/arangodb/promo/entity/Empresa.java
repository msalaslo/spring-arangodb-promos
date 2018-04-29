package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("empresas")
@HashIndex(fields = { "cempresa"}, unique = true)
public class Empresa implements Promocionable,RelacionableParent{
	
	@Id
    public String id;
		
	public String cempresa;
	
	public String name;
	
	@Relations(edges = EmpresaPromocion.class, lazy = true)
	public Collection<Promocion> promociones;
	
	public Collection<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(Collection<Promocion> promociones) {
		this.promociones = promociones;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Empresa(String cempresa, String name) {
		super();
		this.cempresa = cempresa;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCempresa() {
		return cempresa;
	}

	public void setCempresa(String cempresa) {
		this.cempresa = cempresa;
	}

}
