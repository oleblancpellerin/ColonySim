package net.ddns.djeezuss.goap;

import net.ddns.djeezuss.goap.actions.GoapAction;

import java.util.ArrayList;

class GoapActionNode
{
	GoapAction action;
	private ArrayList<GoapActionNode> parents = new ArrayList<>();
	private ArrayList<GoapActionNode> childs = new ArrayList<>();

	GoapActionNode(GoapAction ref) { this.action = ref; }

	void addParent(GoapActionNode a) { this.parents.add(a); }

	void addChild(GoapActionNode a) { this.childs.add(a); }

	ArrayList<GoapActionNode> getParents() { return this.parents; }

	ArrayList<GoapActionNode> getChilds() { return this.childs; }

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (GoapActionNode node : this.getParents())
			str.append("{").append(node.action.getActionName()).append("}");

		str.append(" ---> [").append(this.action.getActionName()).append("] ---> ");

		for (GoapActionNode node : this.getChilds())
			str.append("{").append(node.action.getActionName()).append("}");

		return str.toString();
	}
}
