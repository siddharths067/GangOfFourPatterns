package com.example.designpatterns.composite;

import java.util.AbstractMap.SimpleImmutableEntry;

public class SegmentTreeLeaf implements SegmentTreeNode {

	private final SimpleImmutableEntry<Integer, Integer> bounds;
	private final long element;

	public SegmentTreeLeaf(long element, int index) {
		this.element = element;
		this.bounds = new SimpleImmutableEntry<>(index, index);
	}

	@Override
	public long getSum(int left, int right) {
		validateQuery(left, right);
		return element;
	}

	void validateQuery(int left, int right) {
		assert left >= this.getBounds().getKey() : String.format("Left index in query %d should be greater than equal to left bound %d", left,
				this.getBounds().getKey());
		assert right <= this.getBounds().getValue() : String.format("Right index in query %d should be less than equal to right bound %d", right,
				this.getBounds().getValue());
	}

	@Override
	public SimpleImmutableEntry<Integer, Integer> getBounds() {
		return this.bounds;
	}
}
