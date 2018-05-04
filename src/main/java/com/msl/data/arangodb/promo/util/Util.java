package com.msl.data.arangodb.promo.util;

import java.util.Collection;
import java.util.stream.StreamSupport;

public class Util {
		
	public static int getSize(Iterable<?> iterable) {
		int count = 0; 
		if (iterable instanceof Collection) {
	        count = ((Collection<?>) iterable).size();
	    } else {
	    	long longCount = StreamSupport.stream(iterable.spliterator(), false).count();
	    	count  = Math.toIntExact(longCount);
	    }
		return count;
	}
}
