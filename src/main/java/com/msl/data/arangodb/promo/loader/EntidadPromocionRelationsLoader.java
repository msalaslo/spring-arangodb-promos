package com.msl.data.arangodb.promo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msl.data.arangodb.promo.entity.EntidadPromocionada;
import com.msl.data.arangodb.promo.entity.PromocionEntidad;
import com.msl.data.arangodb.promo.entity.Marca;
import com.msl.data.arangodb.promo.entity.Promocion;
import com.msl.data.arangodb.promo.repository.PromocionEntidadRepository;
import com.msl.data.arangodb.promo.repository.EntidadPromocionadaRepository;
import com.msl.data.arangodb.promo.repository.MarcaRepository;
import com.msl.data.arangodb.promo.repository.PromocionRepository;


@Component
public class EntidadPromocionRelationsLoader {
	@Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private PromocionRepository promocionRepo;
	
	@Autowired
	private PromocionEntidadRepository promocionentidadRepo;

	@Autowired
	private EntidadPromocionadaRepository entidadPromocionada;
	
	public void load() {
		Iterable<Promocion> promociones = promocionRepo.findAll();
		
		Iterable<Marca> marcas = marcaRepo.findAll();
		System.out.println("Cargando promociones en marcas");
		for (Promocion promocion : promociones) {
			for (EntidadPromocionada entidad : marcas) {
				promocionentidadRepo.save(new PromocionEntidad(promocion, entidad));
			}	
		}
		
		Iterable<EntidadPromocionada> marcasPromocionadas = entidadPromocionada.findAll();
		for (EntidadPromocionada entidadPromocionada : marcasPromocionadas) {
			promociones = entidadPromocionada.getPromociones();
			promociones.forEach(System.out::println);
		}
	}
	
	public void deleteAll() {
		promocionentidadRepo.deleteAll();
	}
}
