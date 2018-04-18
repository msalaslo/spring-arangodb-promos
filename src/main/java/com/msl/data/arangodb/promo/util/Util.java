package com.msl.data.arangodb.promo.util;

import java.util.stream.StreamSupport;

public class Util {
	
	public static int getSizeOld(Iterable<?> iterable) {
		int size = 0;
		for (Object object : iterable) {
			size++;
		}
		return size;
	}
	
	public static int getSize(Iterable<?> iterable) {
		long count = StreamSupport.stream(iterable.spliterator(), false).count();
		return Math.toIntExact(count);
	}
	
	

}
