package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;

public class EmptyAction extends GoapAction
{
	public EmptyAction()
	{
		actionName = "empty";
	}

	@Override
	public boolean perform(Colonist colonist)
	{
		return true;
	}
	
	@Override
	public boolean prerequisite()
	{
		return true;
	}
}
