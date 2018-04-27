package com.msl.data.arangodb.promo.entity;

import java.util.Collection;

public interface Promocionable {
	
	public Iterable<Promocion> getPromociones();
	public void setPromociones(Collection<Promocion> promociones);

}
