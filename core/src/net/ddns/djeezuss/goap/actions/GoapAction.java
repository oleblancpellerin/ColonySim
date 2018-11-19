package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public abstract class GoapAction
{
	protected ArrayList<String> prerequisites;
	protected ArrayList<String> effects;
	protected String actionName;
	protected int cost;
	protected boolean done;
	
	public GoapAction()
	{
		this.prerequisites = new ArrayList<>();
		this.effects = new ArrayList<>();
		this.actionName = "";
		this.done = false;
		this.cost = -1;
	}
	
	public ArrayList<String> getPrerequisites() { return this.prerequisites; }
	public ArrayList<String> getEffects() { return this.effects; }
	public String getActionName() { return this.actionName; }
	public int getCost() { return this.cost; }
	
	public abstract void perform(Colonist colonist);
	
	@Override
	public String toString()
	{
		return "GoapAction [" + actionName + "]";
	}
}
