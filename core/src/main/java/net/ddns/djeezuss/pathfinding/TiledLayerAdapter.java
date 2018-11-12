package net.ddns.djeezuss.pathfinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TiledLayerAdapter
{
	public static boolean[][] getBooleanMatrix(TiledMapTileLayer layer)
	{
		final int width = layer.getWidth();
		final int height = layer.getHeight();
		boolean[][] matrix = new boolean[width][height];
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				matrix[i][j] = (layer.getCell(i, j) == null);
			}
		}
		
		return matrix;
	}
}
