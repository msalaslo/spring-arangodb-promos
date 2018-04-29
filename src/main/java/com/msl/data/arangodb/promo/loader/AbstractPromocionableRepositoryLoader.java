package com.msl.data.arangodb.promo.loader;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.PromocionRepository;
import com.msl.data.arangodb.promo.util.Util;

public abstract class AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader {
	
	@Autowired
	private PromocionRepository promocionRepo;
	
	@Override
	public void loadPromociones(Iterable<Promocionable> promocionables) {
		// first create some relations for the marcas and promociones		
		Iterable<Promocion> promociones = promocionRepo.findAll();
		Iterator<Promocion> itePromociones = promociones.iterator();
		int numPromociones = Util.getSize(promociones);
		int numPromocionables = Util.getSize(promocionables);
		int section = numPromocionables/numPromociones;
		int cont = 1;
		Promocion promocion = itePromociones.next();
		for (Promocionable promocionable : promocionables) {		
			System.out.println("Asociando la promocion " + promocion + " al promocionable " + promocionable );
			this.save(promocionable, promocion);
			//Vamos asociando los promocionables a un conjunto equitativo de promociones
			if(cont == section) {
				if(itePromociones.hasNext()) {
					promocion = itePromociones.next();
				}
				cont = 1;
			}else {
				cont++;
			}			
		}				
	}
	
	public abstract void save(Promocionable promocionable, Promocion promocion);

}
