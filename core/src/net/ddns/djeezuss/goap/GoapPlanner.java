package net.ddns.djeezuss.goap;

import net.ddns.djeezuss.goap.actions.*;

import java.util.HashMap;
import java.util.Stack;

public class GoapPlanner
{
	private boolean initPhaseDone = false;
	
	private HashMap<String, GoapAction> allActions;
	
	public void init()
	{
		if (!initPhaseDone)
		{
			allActions = new HashMap<>();
			
			addActions();
			
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
	
	private void addAction(GoapAction action)
	{
		if (!initPhaseDone)
			allActions.put(action.getActionName(), action);
	}
	
	public GoapAction getAction(String name)
	{
		return allActions.get(name);
	}
	
	private GoapAction actionsForGoal(GoapAction goal)
	{
		if (goal.getPrerequisites().size() == 0)
		{
			System.out.println(goal.toString());
			return goal;
		}
		
		for (GoapAction action : allActions.values())
		{
			for (String pre : goal.getPrerequisites())
			{
				if (action.getEffects().contains(pre))
				{
					System.out.println(goal.toString());
					return actionsForGoal(action);
				} else if (action.getPrerequisites().size() == 0)
				{
					System.out.println(action.toString());
					return action;
				}
			}
		}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		GoapPlanner goap = new GoapPlanner();
		goap.init();
		goap.actionsForGoal(goap.getAction("makeFireWood"));
	}
}
