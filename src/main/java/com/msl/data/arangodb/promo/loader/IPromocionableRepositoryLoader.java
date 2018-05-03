package com.msl.data.arangodb.promo.loader;

import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;

public interface IPromocionableRepositoryLoader {
	public void loadPromociones();
	public void deletePromociones();
	public void loadPromociones(Iterable<Promocionable> promocionables);
	public void save(Promocionable promocionable, Promocion promocion);
}
