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
	public void perform(Colonist colonist)
	{

	}
}
