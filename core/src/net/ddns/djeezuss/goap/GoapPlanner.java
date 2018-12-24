package net.ddns.djeezuss.goap;

import net.ddns.djeezuss.goap.actions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class GoapPlanner
{
	private HashMap<String, GoapAction> allActions;
	private boolean initPhaseDone = false;
	private GoapNodeNetwork nodeNetwork;

	public void init()
	{
		if (!initPhaseDone)
		{
			allActions = new HashMap<>();
			addActions();

			nodeNetwork = new GoapNodeNetwork(allActions);

			initPhaseDone = true;
		}
	}

	private void addActions()
	{
		addAction(new CutWoodAction());
		addAction(new GatherWoodAction());
		addAction(new GetToolAction("Axe"));
		addAction(new MakeFireWoodAction());
	}

	public GoapAction getAction(String name)
	{
		return allActions.get(name);
	}

	public Stack<GoapAction> actionsForGoal(GoapAction goal)
	{
		Stack<GoapAction> actionsToPerform = new Stack<>();

		GoapActionNode node = nodeNetwork.getNodeFromName(goal.getActionName());
		actionsToPerform.push(node.action);
		while (!nodeNetwork.head.getChilds().contains(node))
		{
			ArrayList<GoapActionNode> parents = null;
			if (node != null) parents = node.getParents();

			node = null;
			if (parents != null)
			{
				for (GoapActionNode n : parents)
				{
					if (node == null || node.action.getCost() > n.action.getCost())
					{
						node = n;
					}
				}
			}

			if (node != null) actionsToPerform.push(node.action);
		}

		return actionsToPerform;
	}

	private void addAction(GoapAction action)
	{
		if (!initPhaseDone)
			allActions.put(action.getActionName(), action);
	}

	private ArrayList<GoapAction> getActionFromPrerequisite(String prerequisite)
	{
		ArrayList<GoapAction> actions = new ArrayList<>();
		for (GoapAction action : allActions.values())
		{
			if (action.getEffect().contains(prerequisite))
				actions.add(action);
		}

		return actions;
	}
}
