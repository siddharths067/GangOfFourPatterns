package com.example.designpatterns.composite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import org.junit.jupiter.api.Test;

class SegmentTreeCompositeTest {

	@Test
	void getSum() {
		SegmentTreeNode segmentTreeNode = SegmentTreeComposite.constructTree(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
		assertEquals(6, segmentTreeNode.getSum(0, 2));
		assertEquals(7, segmentTreeNode.getSum(2, 3));
		assertEquals(15, segmentTreeNode.getSum(3, 5));
		assertEquals(28, segmentTreeNode.getSum(0, 6));
	}

	@Test
	void constructTree() {
		SegmentTreeNode segmentTreeNode = SegmentTreeComposite.constructTree(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
		assertEquals(28, segmentTreeNode.getSum(0, 6));
	}

	@Test
	void getBounds() {
		SegmentTreeNode segmentTreeNode = SegmentTreeComposite.constructTree(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
		assertEquals(new SimpleImmutableEntry<>(0, 6), segmentTreeNode.getBounds());
	}
}