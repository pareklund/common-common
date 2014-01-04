package com.anygine.common.common.function;

import java.util.ArrayList;
import java.util.Collection;

public class Filter<T> {
	
	public Collection<T> apply(Collection<T> ts, Predicate<T> predicate) {
		Collection<T> results = new ArrayList<T>(ts.size());
		for (T t : ts) {
			if (predicate.apply(t)) {
				results.add(t);
			}
		}
		return results;
	}
}
