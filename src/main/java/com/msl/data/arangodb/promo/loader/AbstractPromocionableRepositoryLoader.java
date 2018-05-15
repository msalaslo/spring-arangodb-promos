package com.msl.data.arangodb.promo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.entity.Promocionable;
import com.msl.data.arangodb.promo.repository.PromocionRepository;

public abstract class AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractPromocionableRepositoryLoader.class.getName());

	@Autowired
	private PromocionRepository promocionRepo;
	
	public abstract void save(Promocionable promocionable, Promocion promocion);
	
	@Override
	public void loadPromociones(Iterable<Promocionable> promocionables) {
		logger.debug("Cargando promociones");
	    Long numPromociones = promocionRepo.count();
		for (Promocionable promocionable : promocionables) {	
			Promocion promocion = randomPromocion(numPromociones);
			logger.debug("Asociando la promocion " + promocion + " al promocionable " + promocionable );
			this.save(promocionable, promocion);		
		}
	}
	
	public Promocion randomPromocion(long numPromociones) {
		logger.debug("Recogiendo promocion aleatoria");
	    int idx = (int)(Math.random() * numPromociones);
	    Page<Promocion> promocionPage = promocionRepo.findAll(PageRequest.of(idx, 1));
	    Promocion q = null;
	    if (promocionPage.hasContent()) {
	        q = promocionPage.getContent().get(0);
	    }
		logger.debug("Promocion aleatoria recogida");
	    return q;
	}
}
