package com.msl.data.arangodb.promo.loader;

import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;

public interface IRelacionableRepositoryLoader {
	public void loadRelaciones();
	public void loadRelaciones(Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents);
	public void save(Relacionable relacionable, RelacionableParent parent);
}
