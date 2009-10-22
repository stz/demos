package org.klab.demo.googlecollections;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class MultisetTest {

	@Test
	public void test1() {
		ImmutableList<Integer> numbers = ImmutableList.of(2,3,0,1,0,1,2,3,2,3);
		Multiset<Integer> multiset = TreeMultiset.create(numbers);
		
		Assert.assertEquals(2, multiset.count(0));
		multiset.remove(0); // Decreasing the count for 0 by 1
		Assert.assertEquals(1, multiset.count(0));

		Assert.assertEquals(2, multiset.count(1));
		multiset.add(1, 4); // Increasing the count for 1 by 4
		Assert.assertEquals(6, multiset.count(1));
	}
}
