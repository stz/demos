package org.klab.demo.googlecollections;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

public class BiMapTest {

	@Test
	public void test1() {
		ImmutableBiMap<Integer, String> biMap = ImmutableBiMap.of(0, "Zero", 1, "One", 2, "Two", 3, "Three");
		Assert.assertEquals("2 should be mapped to Two", "Two" , biMap.get(2));

		BiMap<String, Integer> inverseBiMap = biMap.inverse();
		Assert.assertEquals("Two should be mapped to 2", Integer.valueOf(2), inverseBiMap.get("Two"));
	}
}
