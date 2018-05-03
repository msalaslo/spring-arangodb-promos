package com.msl.data.arangodb.promo.loader;

import java.util.Iterator;

import com.msl.data.arangodb.promo.entity.Relacionable;
import com.msl.data.arangodb.promo.entity.RelacionableParent;
import com.msl.data.arangodb.promo.util.Util;

public abstract class AbstractRelacionableRepositoryLoader implements IRelacionableRepositoryLoader{
	
	public void loadRelaciones(Iterable<Relacionable> relacionables, Iterable<RelacionableParent> parents) {
		int numParents = Util.getSize(parents);
		int numRelacionables = Util.getSize(relacionables);
		Iterator<RelacionableParent> ite = parents.iterator();
		int section = numRelacionables/numParents;
		int cont = 1;
		RelacionableParent parent = ite.next();
		System.out.println("Asociando el padre " + parent);
		for (Relacionable relacionable : relacionables) {		
//			System.out.println("Asociando el padre " + parent + " al relacionable " + relacionable );
			this.save(relacionable, parent);
			if(cont == section) {
				System.out.println("Cambiando el padre " + parent);
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
