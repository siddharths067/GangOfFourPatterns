package com.example.designpatterns.composite;

import java.util.AbstractMap;

public interface SegmentTreeNode {
	long getSum(int left, int right);
	AbstractMap.SimpleImmutableEntry<Integer, Integer> getBounds();
}
