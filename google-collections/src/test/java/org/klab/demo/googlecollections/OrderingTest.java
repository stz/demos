package org.klab.demo.googlecollections;


import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

public class OrderingTest {

	@Test
	public void test1() {
		Function<Fruit, Color> getColorFunction = new Function<Fruit, Color>() {
		    public Color apply(Fruit from) {
		        return from.getColor();
		    }
		};
		 
		Function<Fruit, String> getNameFunction = new Function<Fruit, String>() {
		    public String apply(Fruit from) {
		        return from.getName();
		    }
		};
		 
		Ordering<Fruit> colorOrdering = Ordering.natural().onResultOf(getColorFunction);
		Ordering<Fruit> nameOrdering = Ordering.natural().onResultOf(getNameFunction);
		 
		Ordering<Fruit> colorAndNameOrdering = colorOrdering.compound(nameOrdering);
		 
		Set<Fruit> fruits = new HashSet<Fruit>();
		ImmutableSortedSet<Fruit> sortedFruits = ImmutableSortedSet.orderedBy(colorAndNameOrdering).addAll(fruits).build();
		 
		System.out.println(Joiner.on(", ").join(sortedFruits));
	}
}
