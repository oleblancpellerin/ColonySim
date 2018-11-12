package net.ddns.djeezuss.pathfinding;

public class Node
{
	public int x, y;
	double hValue;
	int gValue;
	double fValue;
	Node parent;
	
	Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
