package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

public class MakeFireWoodAction extends GoapAction
{
	public MakeFireWoodAction()
	{
		super();

		actionName = "makeFireWood";
		prerequisite = "hasWood";
		effect = "hasFireWood";
		cost = 5;
	}

	@Override
	public boolean perform(Colonist colonist)
	{
		return false;
	}
	
	@Override
	public boolean prerequisite()
	{
		return false;
	}
}
