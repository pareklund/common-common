package com.anygine.common.common.function;

public class Predicates {
	
	@SuppressWarnings("rawtypes")
	private static final Predicate TRUE = new Predicate() {
		public boolean apply(Object o) { return true;}
	};
	
	@SuppressWarnings("unchecked")
	public static final <T> Predicate<T> truePredicate() { 
		return (Predicate<T>) TRUE;
	}
}
