package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("marcas")
@HashIndex(fields = { "cmarmuma"}, unique = true)
public class Marca implements Promocionable,RelacionableParent{
			
	@Id
	public String id;
		
	private String cmarmuma;
	
	private String name; 
			
	@Relations(edges = MarcaPromocion.class, lazy = true)
	public Collection<Promocion> promociones;
	
	public Marca(String cmarmuma, String name) {
		super();
		this.cmarmuma = cmarmuma;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Iterable<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(Collection<Promocion> promociones) {
		this.promociones = promociones;
	}

	public String getCmarmuma() {
		return cmarmuma;
	}

	public void setCmarmuma(String cmarmuma) {
		this.cmarmuma = cmarmuma;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    @Override
    public String toString() {
        return new ToStringBuilder(this).
        		append("id", id).
                append("cmarmuma", cmarmuma).
                append("name", name).
                append("promociones", promociones).
        		toString();
    }
}
