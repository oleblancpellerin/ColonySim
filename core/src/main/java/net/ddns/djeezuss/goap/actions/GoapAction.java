package net.ddns.djeezuss.goap.actions;

import javafx.util.Pair;
import net.ddns.djeezuss.Colonist;

import java.util.HashSet;

public abstract class GoapAction
{
	/**
	 * The cost of performing the action.
	 * Figure out a weight that suits the action.
	 * Changing it will affect what actions are chosen during planning.
	 */
	public float cost = 1f;
	/**
	 * An action often has to perform on an object. This is that object. Can be null.
	 */
	public Colonist target;
	private HashSet<Pair<String, Object>> preconditions;
	private HashSet<Pair<String, Object>> effects;
	private boolean inRange = false;

	public GoapAction()
	{
		preconditions = new HashSet<>();
		effects = new HashSet<>();
	}

	public void doReset()
	{
		inRange = false;
		target = null;
		reset();
	}

	/**
	 * Reset any variables that need to be reset before planning happens again.
	 */
	public abstract void reset();

	/**
	 * Is the action done?
	 */
	public abstract boolean isDone();

	/**
	 * Procedurally check if this action can run. Not all actions
	 * will need this, but some might.
	 */
	public abstract boolean checkProceduralPrecondition(Colonist agent);

	/**
	 * Run the action.
	 * Returns True if the action performed successfully or false
	 * if something happened and it can no longer perform. In this case
	 * the action queue should clear out and the goal cannot be reached.
	 */
	public abstract boolean perform(Colonist agent);

	/**
	 * Does this action need to be within range of a target game object?
	 * If not then the moveTo state will not need to run for this action.
	 */
	public abstract boolean requiresInRange();

	/**
	 * Are we in range of the target?
	 * The MoveTo state will set this and it gets reset each time this action is performed.
	 */
	public boolean isInRange()
	{
		return inRange;
	}

	public void setInRange(boolean inRange)
	{
		this.inRange = inRange;
	}


	public void addPrecondition(String key, Object value)
	{
		preconditions.add(new Pair<>(key, value));
	}

	public void removePrecondition(String key)
	{
		Pair<String, Object> remove = null;
		for (Pair<String, Object> kvp : preconditions)
		{
			if (kvp.getKey().equals(key))
				remove = kvp;
		}
		if (remove != null)
			preconditions.remove(remove);
	}

	public void addEffect(String key, Object value)
	{
		effects.add(new Pair<>(key, value));
	}

	public void removeEffect(String key)
	{
		Pair<String, Object> remove = null;
		for (Pair<String, Object> kvp : effects)
		{
			if (kvp.getKey().equals(key))
				remove = kvp;
		}
		if (remove != null)
			effects.remove(remove);
	}

	public HashSet<Pair<String, Object>> getPreconditions()
	{
		return preconditions;
	}

	public HashSet<Pair<String, Object>> getEffects()
	{
		return effects;
	}
}
