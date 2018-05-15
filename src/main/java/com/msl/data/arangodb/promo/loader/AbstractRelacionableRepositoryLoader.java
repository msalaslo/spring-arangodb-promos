package com.msl.data.arangodb.promo.loader;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msl.data.arangodb.promo.entity.EntityUtils;
import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;

public abstract class AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractPromocionableRepositoryLoader.class.getName());

	public abstract void save(Relacionable relacionable, RelacionableParent parent);
	public abstract Iterable<Relacionable> getPage(int page, int pageSize);
	
	public void loadRelacionesSharing(Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents) {
		int numParents = EntityUtils.getSize(parents);
		int numRelacionables = EntityUtils.getSize(relacionables);
		Iterator<RelacionableParent> ite = parents.iterator();
		int section = numRelacionables/numParents;
		int cont = 1;
		RelacionableParent parent = ite.next();
		logger.info("Asociando el padre " + parent + " a un total de " + numRelacionables + " entidades.");
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
	
	public void loadRelacionesPaging(int numRelacionables, Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents) {
		int pageSize = getPageSize(numRelacionables);
		int numPaginas = numRelacionables/pageSize;
		Iterable<RelacionableParent> relParents = EntityUtils.toRelacionableParent(parents); 
		for (int i = 0; i < numPaginas; i++) {
			loadRelaciones(getPage(i, pageSize), relParents);
		}
	}
	
	public void loadRelaciones(Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents) {
		logger.info("Asociando relacionables a entidades padre.");
		List<RelacionableParent> list = EntityUtils.iterableToList(parents);
		relacionables.forEach((r) -> {
			RelacionableParent randomParent = list.get(new Random().nextInt(list.size()));
			this.save(r, randomParent);
		});			
	}
	
	public void save(Relacionable relacionable, Optional<RelacionableParent> parent) {
		if(parent.isPresent()) {
			this.save(relacionable, parent.get());
		}
	}
	
	private int getPageSize(int numElements) {
		int MAX_PAGE_SIZE = 1000;
		int pageSize = MAX_PAGE_SIZE;
		while(numElements/pageSize==0) {
			pageSize = pageSize/10;
		}
		return pageSize;
	}
}
