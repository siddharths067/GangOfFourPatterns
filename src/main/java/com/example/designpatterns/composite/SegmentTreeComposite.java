package com.example.designpatterns.composite;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.List;

public class SegmentTreeComposite implements SegmentTreeNode {

	private final long subtreeSum;
	private final List<SegmentTreeNode> children;
	private final SimpleImmutableEntry<Integer, Integer> bounds;

	public SegmentTreeComposite(List<SegmentTreeNode> children) {
		this.children = children;
		this.subtreeSum = this.children.stream().reduce(0L, (subtotal, child) -> subtotal + child.getSum(child.getBounds().getKey(), child.getBounds().getValue()), Long::sum);
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		for (SegmentTreeNode child : this.children) {
			left = Math.min(left, child.getBounds().getKey());
			right = Math.max(right, child.getBounds().getValue());
		}
		this.bounds = new SimpleImmutableEntry<>(left, right);
	}

	@Override
	public long getSum(int left, int right) {
		SimpleImmutableEntry<Integer, Integer> bounds = this.getBounds();
		validateQuery(left, right, bounds);
		if (left == bounds.getKey() && right == bounds.getValue())
			// The whole subtree is included
			return this.subtreeSum;
		long sum = 0;
		for(SegmentTreeNode child: children){
			SimpleImmutableEntry<Integer, Integer> childBounds = child.getBounds();
			if(left <= childBounds.getValue() && right >= childBounds.getKey())
				sum += child.getSum(Math.max(left, childBounds.getKey()), Math.min(right, childBounds.getValue()));
		}
		return sum;

	}

	private void validateQuery(int left, int right, SimpleImmutableEntry<Integer, Integer> bounds) {
		assert left <= right : "Query left bound should be lesser than or equal to right bound";
		assert left <= bounds.getValue() && right >= bounds.getKey() : "Query bounds should intersect with subtree range";
	}

	public static SegmentTreeNode constructTree(List<Long> elements){
		int index = 0;
		List<SegmentTreeNode> segmentTreeNodes = generateLeaves(elements, index);
		while(segmentTreeNodes.size() > 1){
			reduceLevel(segmentTreeNodes);
		}
		return segmentTreeNodes.get(0);
	}

	private static void reduceLevel(List<SegmentTreeNode> segmentTreeNodes) {
		List<SegmentTreeNode> newSegmentTreeNodes = new ArrayList<>();
		while(segmentTreeNodes.size() > 0) {
			reduceFirstTwoNodesAndSaveResult(segmentTreeNodes, newSegmentTreeNodes);
		}
		segmentTreeNodes.addAll(newSegmentTreeNodes);
		newSegmentTreeNodes.clear();
	}

	private static void reduceFirstTwoNodesAndSaveResult(List<SegmentTreeNode> segmentTreeLeaves, List<SegmentTreeNode> newSegmentTreeNodes) {
		SegmentTreeNode a = segmentTreeLeaves.get(0);
		segmentTreeLeaves.remove(0);
		if(segmentTreeLeaves.size() > 0) {
			SegmentTreeNode b = segmentTreeLeaves.get(0);
			segmentTreeLeaves.remove(0);
			newSegmentTreeNodes.add(new SegmentTreeComposite(List.of(a, b)));
		} else {
			newSegmentTreeNodes.add(new SegmentTreeComposite(List.of(a)));
		}
	}

	private static List<SegmentTreeNode> generateLeaves(List<Long> elements, int index) {
		List<SegmentTreeNode> segmentTreeLeaves = new ArrayList<>();
		for(Long element: elements){
			segmentTreeLeaves.add(new SegmentTreeLeaf(element, index));
			index += 1;
		}
		return segmentTreeLeaves;
	}

	@Override
	public SimpleImmutableEntry<Integer, Integer> getBounds() {
		return this.bounds;
	}
}
