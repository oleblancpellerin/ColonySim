package net.ddns.djeezuss.pathfinding;

class Node
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
}
