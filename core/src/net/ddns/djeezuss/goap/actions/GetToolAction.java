package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;
import org.apache.commons.lang3.StringUtils;

public class GetToolAction extends GoapAction
{
	public GetToolAction(String toolName)
	{
		super();
		toolName = StringUtils.capitalize(toolName.toLowerCase());
		actionName = "getTool" + toolName;

		effect = "hasTool" + toolName;
		cost = 2;
	}

	@Override
	public void perform(Colonist colonist)
	{

	}
}
