package net.ddns.djeezuss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Colonist implements Tickable
{
	private final TiledMap tiledMap;
	private final TiledMapTileLayer collisionLayer;
	private final SpriteBatch renderer;

	private Point position;
	private int state = 0;
	private long accumulated_time = 0;

	public Colonist(TiledMap tiledMap, SpriteBatch spriteRenderer)
	{
		this.renderer = spriteRenderer;
		this.tiledMap = tiledMap;
		this.collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Collision");

		position = new Point(MathUtils.random(collisionLayer.getWidth()), MathUtils.random(collisionLayer.getHeight()));
	}

	@Override
	public void update(long delta)
	{
		accumulated_time += delta;

		if (accumulated_time >= 100)
		{
			accumulated_time -= 100;
			switch (state)
			{
				case 0:
					position.x++;
					state++;
					break;
				case 1:
					position.y--;
					state++;
					break;
				case 2:
					position.x--;
					state++;
					break;
				case 3:
					position.y++;
					state = 0;
					break;
			}
		}
	}

	@Override
	public void draw()
	{

	}

	@Override
	public void dispose()
	{

	}
}
