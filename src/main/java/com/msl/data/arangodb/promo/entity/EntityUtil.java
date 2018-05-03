package com.msl.data.arangodb.promo.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityUtil {
	
	public static List<Relacionable> centroToRelacionable(Iterable<Centro> centros){
		List<Relacionable> relacionables = new ArrayList<Relacionable>();
		for (Centro centro : centros) {
			relacionables.add((Relacionable)centro);
		}
		return relacionables;
	}
	
	public static List<RelacionableParent> empresaToRelacionableParent(Iterable<Empresa> empresas){
		List<RelacionableParent> relacionables = new ArrayList<RelacionableParent>();
		for (Empresa empresa : empresas) {
			relacionables.add((RelacionableParent)empresa);
		}
		return relacionables;
	}

}
