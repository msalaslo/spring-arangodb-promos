package com.msl.data.arangodb.promo.loader;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.util.Util;

public abstract class AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractPromocionableRepositoryLoader.class.getName());

	
	public void loadRelaciones(Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents) {
		int numParents = Util.getSize(parents);
		int numRelacionables = Util.getSize(relacionables);
		Iterator<RelacionableParent> ite = parents.iterator();
		int section = numRelacionables/numParents;
		int cont = 1;
		RelacionableParent parent = ite.next();
		logger.debug("Asociando el padre " + parent);
		for (Relacionable relacionable : relacionables) {		
//			logger.debug("Asociando el padre " + parent + " al relacionable " + relacionable );
			this.save(relacionable, parent);
			if(cont == section) {
				logger.debug("Cambiando el padre " + parent);
				if(ite.hasNext()) {
					parent = ite.next();
				}
				cont = 1;
			}else {
				cont++;
			}			
		}				
	}
	
	public abstract void save(Relacionable relacionable, RelacionableParent parent);

}
