package org.klab.demo.googlecollections;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class IterablesTest {

	@Test
	public void test1() {
		Predicate<Integer> evenPredicate = new Predicate<Integer>() {
			public boolean apply(Integer input) {
				return input % 2 == 0;
			}
		};

		Set<Integer> numbers = new HashSet<Integer>();
		Iterable<Integer> evenNumbers = Iterables
				.filter(numbers, evenPredicate);

		Function<Integer, Integer> squareFunction = new Function<Integer, Integer>() {
			public Integer apply(Integer from) {
				return from * from;
			}
		};

		Iterable<Integer> squareOfEvenNumbers = Iterables.transform(evenNumbers, squareFunction);
	}
}
