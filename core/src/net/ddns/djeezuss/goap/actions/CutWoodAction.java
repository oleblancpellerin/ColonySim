package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

public class CutWoodAction extends GoapAction
{
	public CutWoodAction()
	{
		super();

		actionName = "cutWood";
		prerequisite = "hasToolAxe";
		effect = "hasWood";
		cost = 4;
	}

	@Override
	public void perform(Colonist colonist)
	{

	}
}
