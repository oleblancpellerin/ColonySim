package net.ddns.djeezuss.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathFindingAStar
{
	private static Node[][] cell;
	private static ArrayList<Node> pathList = new ArrayList<Node>();
	private static ArrayList<Node> closedList = new ArrayList<Node>();
	
	public static void generateHValue(boolean matrix[][], int startX, int startY,
									  int destX, int destY, int width, int height, int cost)
	{
		cell = new Node[width][height];
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				//noinspection SuspiciousNameCombination
				cell[y][x] = new Node(y, x);
				
				if (matrix[y][x])
				{
					cell[y][x].hValue = Math.abs(x - destX) + Math.abs(y - destY);
				} else
				{
					cell[y][x].hValue = -1;
				}
			}
		}
		
		generatePath(cell, startX, startY, destX, destY, width, height, cost);
	}
	
	private static void generatePath(Node[][] cell, int startX, int startY, int destX, int destY, int width, int height, int cell_move_cost)
	{
		PriorityQueue<Node> openList = new PriorityQueue<Node>(11, new NodeComparator());
		
		openList.add(cell[startX][startY]);
		
		boolean loop = true;
		while (loop)
		{
			Node node = openList.poll();
			
			if (node == null) loop = false;
			if (node == cell[destX][destY])
			{
				closedList.add(node);
				loop = false;
			}
			
			
			if (loop)
			{
				closedList.add(node);
				
				// Left cell
				if (node.y - 1 >= 0 &&
						cell[node.x][node.y - 1].hValue != -1 &&
						!openList.contains(cell[node.x][node.y - 1]) &&
						!closedList.contains(cell[node.x][node.y - 1]))
				{
					double tCost = node.fValue + cell_move_cost;
					cell[node.x][node.y - 1].gValue = cell_move_cost;
					double cost = cell[node.x][node.y - 1].hValue + tCost;
					
					if (cell[node.x][node.y - 1].fValue > cost || !openList.contains(cell[node.x][node.y - 1]))
						cell[node.x][node.y - 1].fValue = cost;
					
					openList.add(cell[node.x][node.y - 1]);
					cell[node.x][node.y - 1].parent = node;
				}
				
				// Right cell
				if (node.y + 1 < width &&
						cell[node.x][node.y + 1].hValue != -1 &&
						!openList.contains(cell[node.x][node.y + 1]) &&
						!closedList.contains(cell[node.x][node.y + 1]))
				{
					double tCost = node.fValue + cell_move_cost;
					cell[node.x][node.y + 1].gValue = cell_move_cost;
					double cost = cell[node.x][node.y + 1].hValue + tCost;
					
					if (cell[node.x][node.y + 1].fValue > cost || !openList.contains(cell[node.x][node.y + 1]))
						cell[node.x][node.y + 1].fValue = cost;
					
					openList.add(cell[node.x][node.y + 1]);
					cell[node.x][node.y + 1].parent = node;
				}
				
				// Top cell
				if (node.x + 1 < height &&
						cell[node.x + 1][node.y].hValue != -1 &&
						!openList.contains(cell[node.x][node.y + 1]) &&
						!closedList.contains(cell[node.x][node.y + 1]))
				{
					double tCost = node.fValue + cell_move_cost;
					cell[node.x + 1][node.y].gValue = cell_move_cost;
					double cost = cell[node.x + 1][node.y].hValue + tCost;
					
					if (cell[node.x + 1][node.y].fValue > cost || !openList.contains(cell[node.x + 1][node.y]))
						cell[node.x + 1][node.y].fValue = cost;
					
					openList.add(cell[node.x + 1][node.y]);
					cell[node.x + 1][node.y].parent = node;
				}
				
				// Bottom cell
				if (node.x - 1 >= 0 &&
						cell[node.x - 1][node.y].hValue != -1 &&
						!openList.contains(cell[node.x][node.y + 1]) &&
						!closedList.contains(cell[node.x][node.y + 1]))
				{
					double tCost = node.fValue + cell_move_cost;
					cell[node.x - 1][node.y].gValue = cell_move_cost;
					double cost = cell[node.x - 1][node.y].hValue + tCost;
					
					if (cell[node.x - 1][node.y].fValue > cost || !openList.contains(cell[node.x - 1][node.y]))
						cell[node.x - 1][node.y].fValue = cost;
					
					openList.add(cell[node.x - 1][node.y]);
					cell[node.x - 1][node.y].parent = node;
				}
				
				Node endNode = closedList.get(closedList.size() - 1);
				
				while (endNode.parent != null)
				{
					pathList.add(endNode);
					endNode = endNode.parent;
				}
				
				pathList.add(cell[startX][startY]);
				
				openList.clear();
			}
		}
	}
}
