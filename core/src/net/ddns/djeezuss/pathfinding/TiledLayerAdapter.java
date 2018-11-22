package net.ddns.djeezuss.pathfinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TiledLayerAdapter
{
	public static boolean[][] getBooleanMatrix(TiledMapTileLayer layer)
	{
		final int width = layer.getWidth();
		final int height = layer.getHeight();
		boolean[][] matrix = new boolean[width][height];
		
		for (int y = 0; y < width; y++)
		{
			for (int x = 0; x < height; x++)
			{
				matrix[y][x] = (layer.getCell(x, y) == null);
			}
		}
		
		return matrix;
	}
}
