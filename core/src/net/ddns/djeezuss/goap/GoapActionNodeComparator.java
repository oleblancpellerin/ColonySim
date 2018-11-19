package net.ddns.djeezuss.goap;

import java.util.Comparator;

public class GoapActionNodeComparator implements Comparator<GoapActionNode>
{
	@Override
	public int compare(GoapActionNode node1, GoapActionNode node2)
	{
		return Integer.compare(node1.action.getCost(), node2.action.getCost());
	}
}
