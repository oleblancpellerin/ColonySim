package net.ddns.djeezuss.pathfinding;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NodeComparatorTest
{
	@Test
	void compare()
	{
		PriorityQueue<Node> nodes = new PriorityQueue<>(11, new NodeComparator());
		Node n1 = new Node(0, 0);
		n1.fValue = 10;
		Node n2 = new Node(0, 1);
		n2.fValue = 20;
		Node n3 = new Node(0, 2);
		n3.fValue = 30;

		nodes.add(n1);
		nodes.add(n3);
		nodes.add(n2);

		assertEquals(10, Objects.requireNonNull(nodes.poll()).fValue);
		assertEquals(20, Objects.requireNonNull(nodes.poll()).fValue);
		assertEquals(30, Objects.requireNonNull(nodes.poll()).fValue);
	}
}