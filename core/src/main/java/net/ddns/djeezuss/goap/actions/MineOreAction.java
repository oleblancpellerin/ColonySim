package net.ddns.djeezuss.goap.actions;

import net.ddns.djeezuss.Colonist;
import net.ddns.djeezuss.components.IronRockComponent;

public class MineOreAction extends GoapAction
{
	public float miningDuration = 2; // seconds
	private boolean mined = false;
	private Object /*IronOreComponent*/ targetRock; // where we get the ore from
	private float startTime = 0;

	public MineOreAction(IronRockComponent[] rocks)
	{
		addPrecondition("hasTool", true); // we need a tool to do this
		addPrecondition("hasOre", false); // if we have the ore we don't want more
		addEffect("hasOre", true);
	}

	@Override
	public void reset()
	{
		mined = false;
		targetRock = null;
		startTime = 0;
	}

	@Override
	public boolean isDone()
	{
		return mined;
	}

	@Override
	public boolean checkProceduralPrecondition(Colonist agent)
	{
		// find the nearest rock that we can mine
		/*IronRockComponent[] rocks = FindObjectsOfType(typeof(IronRockComponent)) as IronRockComponent[];
		IronRockComponent closest = null;
		float closestDist = 0;

		for (IronRockComponent rock : rocks)
		{
			if (closest == null)
			{
				// first one, so choose it for now
				closest = rock;
				closestDist = (rock.gameObject.transform.position - agent.transform.position).magnitude;
			} else
			{
				// is this one closer than the last?
				float dist = (rock.gameObject.transform.position - agent.transform.position).magnitude;
				if (dist < closestDist)
				{
					// we found a closer one, use it
					closest = rock;
					closestDist = dist;
				}
			}
		}
		targetRock = closest;
		target = targetRock.gameObject;

		return closest != null;*/
		return false;
	}

	@Override
	public boolean perform(Colonist agent)
	{
		return false;
	}

	@Override
	public boolean requiresInRange()
	{
		return false;
	}
}
