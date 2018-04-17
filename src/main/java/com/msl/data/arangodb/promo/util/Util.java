package com.msl.data.arangodb.promo.util;

public class Util {
	
	public static int getSize(Iterable<?> iterable) {
		int size = 0;
		for (Object object : iterable) {
			size++;
		}
		return size;
	}

}
