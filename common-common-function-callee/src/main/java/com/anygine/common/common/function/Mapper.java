package com.anygine.common.common.function;

import java.util.Collection;

public interface Mapper<T1, T2> {
	Collection<T2> map(
	    Collection<T1> src, Predicate<T1> srcPredicate, 
	    Predicate<T2> dstPredicate);
	T2 oneToOne(T1 srcItem);
	Collection<T2> oneToMany(T1 srcItem);
}
