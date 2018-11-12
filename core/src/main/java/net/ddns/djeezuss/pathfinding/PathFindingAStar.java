package net.ddns.djeezuss.pathfinding;

import net.ddns.djeezuss.exceptions.PathNotFoundException;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class PathFindingAStar
{
	@SuppressWarnings("SameParameterValue")
	static Stack<Node> AStar(boolean[][] matrix, int startX, int startY, int destX, int destY) throws PathNotFoundException
	{
		Node[][] cells = new Node[matrix.length][matrix[0].length];

		PriorityQueue<Node> openList = new PriorityQueue<>(11, new NodeComparator());
		ArrayList<Node> closedList = new ArrayList<>();

		openList.clear();
		closedList.clear();

		// Generate values
		for (int i = 0; i < cells.length; i++)
		{
			for (int j = 0; j < cells[0].length; j++)
			{
				cells[i][j] = new Node(j, i);
				cells[i][j].fValue = 0;
				cells[i][j].gValue = Integer.MAX_VALUE;
				if (matrix[j][i])
					cells[i][j].hValue = 10 * (Math.abs(startX - destX) + Math.abs(startY - destY));
				else
					cells[i][j].hValue = -1;
			}
		}

		cells[startY][startX].gValue = 0;
		cells[startY][startX].fValue = cells[startY][startX].hValue;

		openList.add(cells[startY][startX]);

		while (!openList.isEmpty())
		{
			Node node = openList.poll();

			if (node.x == destX && node.y == destY)
			{
				// Return the final path

				Stack<Node> path = new Stack<>();

				do
				{
					path.add(node);
					node = node.parent;
				} while (node != null);

				return path;
			}

			closedList.add(node);

			// Bottom
			if (node.y + 1 < cells.length)
				checkNeighbour(openList, closedList, cells[node.y + 1][node.x], node, 10);
			// Top
			if (node.y - 1 >= 0)
				checkNeighbour(openList, closedList, cells[node.y - 1][node.x], node, 10);

			// Right
			if (node.x + 1 < cells.length)
				checkNeighbour(openList, closedList, cells[node.y][node.x + 1], node, 10);
			// Left
			if (node.x - 1 >= 0)
				checkNeighbour(openList, closedList, cells[node.y][node.x - 1], node, 10);
		}

		throw new PathNotFoundException();
	}

	private static void checkNeighbour(PriorityQueue<Node> openList, ArrayList<Node> closedList, Node node, Node active_node, int cost)
	{
		int gPrime = active_node.gValue + cost;

		if (closedList.contains(node) ||
				node.hValue == -1 ||
				gPrime >= node.gValue)
			return;

		if (!openList.contains(node))
			openList.add(node);

		node.parent = active_node;
		node.gValue = gPrime;
	}

	public static void main(String[] args)
	{
		try
		{
			boolean[][] matrix = {
					{true, true, false, true, true},
					{true, true, false, true, true},
					{true, true, false, true, true},
					{true, false, false, true, true},
					{true, true, true, true, true}
			};

			Stack<Node> path = PathFindingAStar.AStar(matrix, 1, 0, 4, 3);
			while (!path.isEmpty())
			{
				Node node = path.pop();
				System.out.println("(" + node.x + ", " + node.y + ")");
			}

		} catch (PathNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
