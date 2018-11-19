package net.ddns.djeezuss.pathfinding;

import net.ddns.djeezuss.exceptions.PathNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PathFindingAStarTest
{

	@Test
	void AStarTest()
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
			assertEquals(11, path.size());

		} catch (PathNotFoundException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
}