package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;

@Document("productos")
@HashIndex(fields = { "referencia"}, unique = true)
public class Producto implements Promocionable,Relacionable{
	
	@Id
    public String id;
	
	public String referencia;
	
	public String name;
	
	@Relations(edges = ProductoPromocion.class, lazy = false)
	private Collection<Promocion> promociones;
	
	@Relations(edges = ProductoMarca.class, lazy = false)
	private Collection<Marca> marcas;
	
	@Relations(edges = ProductoCentro.class, lazy = false)
	private Collection<Centro> centros;
	
	@Relations(edges = ProductoFamilia.class, lazy = false)
	private Collection<Familia> familias;
	
	public Producto(String referencia, String name) {
		super();
		this.referencia = referencia;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(Collection<Promocion> promociones) {
		this.promociones = promociones;
	}
	
	public Collection<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(Collection<Marca> marcas) {
		this.marcas = marcas;
	}
	
    public Collection<Centro> getCentros() {
		return centros;
	}

	public void setCentros(Collection<Centro> centros) {
		this.centros = centros;
	}

	public Collection<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(Collection<Familia> familias) {
		this.familias = familias;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this).
        		append("PRODUCTO:" + name).
        		append("id", id).
                append("referencia", referencia).
                append("promociones", promociones).
          	    append("marcas", marcas).
        		toString();
    }
}
