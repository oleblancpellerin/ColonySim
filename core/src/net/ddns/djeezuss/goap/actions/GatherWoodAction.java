package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

public class GatherWoodAction extends GoapAction
{
	public GatherWoodAction()
	{
		super();
		
		actionName = "gatherWood";
		
		effects.add("hasWood");
		cost = 10;
	}
	
	@Override
	public void perform(Colonist colonist)
	{
	
	}
}
