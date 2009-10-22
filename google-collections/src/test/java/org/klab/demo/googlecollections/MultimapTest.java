package org.klab.demo.googlecollections;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class MultimapTest {

	@Test
	public void test1() {
		Multimap<String, String> bikes = HashMultimap.create();
		bikes.put("1975", "PSV10");
		bikes.put("1975", "PY10");
		bikes.put("1983", "PSX10");

		Assert.assertEquals(2, bikes.get("1975").size());
		Assert.assertTrue(bikes.get("1975").contains("PSV10"));
	}
}
