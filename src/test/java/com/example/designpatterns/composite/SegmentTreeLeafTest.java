package com.example.designpatterns.composite;

import java.util.AbstractMap.SimpleImmutableEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SegmentTreeLeafTest {

	@Test
	void getSum_CorrectQuery_CorrectSumReturned() {
		int element = 12;
		int index = 10;
		SegmentTreeLeaf leaf = new SegmentTreeLeaf(element, index);
		Assertions.assertEquals(element, leaf.getSum(index, index));
	}

	@Test
	void validateQuery_QueryExtraElementRight_ShouldFailOnRightValidation() {
		int index = 10;
		SegmentTreeLeaf segmentTreeLeaf = getSegmentTreeLeafForIndex(index);
		Assertions.assertThrows(AssertionError.class, () -> segmentTreeLeaf.getSum(10, 11));
	}

	@Test
	void validateQuery_QueryExtraElementLeft_ShouldFailOnLeftValidation() {
		int index = 10;
		SegmentTreeLeaf segmentTreeLeaf = getSegmentTreeLeafForIndex(index);
		Assertions.assertThrows(AssertionError.class, () -> segmentTreeLeaf.getSum(9, 10));
	}

	@Test
	void getBounds_BoundsOfASingleElement() {
		int index = 12;
		SegmentTreeLeaf leaf = getSegmentTreeLeafForIndex(index);
		SimpleImmutableEntry<Integer, Integer> bounds = leaf.getBounds();
		assert bounds.getKey() == index && bounds.getValue() == index : "A Leaf should only contain one element";
	}

	private SegmentTreeLeaf getSegmentTreeLeafForIndex(int index) {
		return new SegmentTreeLeaf(10, index);
	}
}