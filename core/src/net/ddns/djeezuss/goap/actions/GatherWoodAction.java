package net.ddns.djeezuss.goap.actions;

import com.badlogic.gdx.Gdx;
import net.ddns.djeezuss.Colonist;

public class GatherWoodAction extends GoapAction
{
	public GatherWoodAction()
	{
		super();
		
		actionName = "gatherWood";
		
		effect = "hasWood";
		cost = 10;
	}
	
	@Override
	public boolean perform(Colonist colonist)
	{
		if(colonist.moveTo(33, 42))
		{
			Gdx.app.log("GatherWood", "done");
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean prerequisite()
	{
		return true;
	}
}
