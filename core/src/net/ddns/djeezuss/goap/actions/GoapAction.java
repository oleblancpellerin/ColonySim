package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

@SuppressWarnings("WeakerAccess")
public abstract class GoapAction
{
	protected String prerequisite;
	protected String effect;
	protected String actionName;
	protected int cost;
	protected boolean done;

	public GoapAction()
	{
		this.prerequisite = "";
		this.effect = "";
		this.actionName = "";
		this.done = false;
		this.cost = -1;
	}

	public String getPrerequisite() { return this.prerequisite; }
	public String getEffect() { return this.effect; }
	public String getActionName() { return this.actionName; }
	public int getCost() { return this.cost; }

	public abstract boolean perform(Colonist colonist);
	public abstract boolean prerequisite();

	@Override
	public String toString()
	{
		return "GoapAction [" + actionName + "]";
	}
}
