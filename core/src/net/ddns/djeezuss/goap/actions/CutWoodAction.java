package net.ddns.djeezuss.goap.actions;

import com.badlogic.gdx.Gdx;
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
