package net.ddns.djeezuss.goap;

import net.ddns.djeezuss.goap.actions.EmptyAction;
import net.ddns.djeezuss.goap.actions.GoapAction;

import java.util.HashMap;

public class GoapNodeNetwork
{
	public GoapActionNode head;
	private HashMap<String, GoapActionNode> allNodes;

	public GoapNodeNetwork(HashMap<String, GoapAction> allActions)
	{
		head = new GoapActionNode(new EmptyAction());
		this.allNodes = new HashMap<>();

		// Pool of nodes
		for (GoapAction a : allActions.values())
			this.allNodes.put(a.getActionName(), new GoapActionNode(a));


		for (GoapActionNode node : allNodes.values())
		{
			if (node.action.getPrerequisite().length() == 0)
				head.addChild(node);

			for (GoapActionNode otherNode : allNodes.values())
			{
				if (node.action == otherNode.action) continue;

				checkLinkage(node, otherNode);
			}
		}
	}

	public GoapActionNode getNodeFromName(String name)
	{
		return allNodes.get(name);
	}

	private void checkLinkage(GoapActionNode a1, GoapActionNode a2)
	{
		if (a1.action.getEffect().equals(a2.action.getPrerequisite()))
		{
			a1.addChild(a2);
			a2.addParent(a1);
		}
	}
}
