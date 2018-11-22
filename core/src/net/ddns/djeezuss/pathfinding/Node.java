package net.ddns.djeezuss.pathfinding;

public class Node
{
	int x, y;
	double hValue;
	int gValue;
	double fValue;
	Node parent;
	
	Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	@Override
	public String toString() { return "(" + getX() + "," + getY() + ")"; }
}
