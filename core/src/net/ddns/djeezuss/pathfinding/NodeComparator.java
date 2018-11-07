package net.ddns.djeezuss.pathfinding;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>
{
	@Override
	public int compare(Node cell1, Node cell2)
	{
		return Double.compare(cell1.fValue, cell2.fValue);
	}
}
