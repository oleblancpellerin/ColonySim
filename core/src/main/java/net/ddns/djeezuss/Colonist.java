package net.ddns.djeezuss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import net.ddns.djeezuss.pathfinding.TiledLayerAdapter;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Colonist
{
	public static SpriteBatch renderer;
	public static Texture maleNPCTexture;
	public static int nbColonists = 0;
	
	static boolean[][] collisionLayer;
	private final int layerWidth;
	private final int layerHeight;

	private TextureRegion sprite;
	
	private int direction = 0;
	private int anim_step = 0;
	private boolean walking = false;
	
	private Point location;
	private int state = 0;
	private int move_anim_state = 0;
	private long accumulated_time = 0;
	private long anim_target_time = 0;
	private long move_target_time = 0;
	private long move_anim_target_time = 0;
	
	public Colonist(int width, int height)
	{
		nbColonists++;

		layerWidth = width;
		layerHeight = height;
		
		//location = new Point(MathUtils.random(collisionLayer.getWidth()), MathUtils.random(collisionLayer.getHeight()));
		location = new Point();
		while (!collisionLayer[location.x][location.y])
		{
			location.x = MathUtils.random(layerWidth - 1);
			location.y = MathUtils.random(layerHeight - 1);
		}
		
		//PathFindingAStar.generateHValue(collisionLayer, location.x, location.y, );
		
		if (maleNPCTexture == null) maleNPCTexture = new Texture("spritesheets/male_villager.png");
		sprite = new TextureRegion(maleNPCTexture, 0, 0, 16, 16);
		
		Gdx.app.debug(getClass().getName(), "x: " + location.x + "\ty: " + location.y);
	}
	
	public void update(long delta)
	{
		accumulated_time += delta;
		
		if (anim_target_time < accumulated_time)
		{
			// Animation TIME !!!
			if (walking)
			{
				if (anim_step < 3)
					anim_step++;
				else
					anim_step = 0;
				
				sprite.setRegion(anim_step * 16, direction * 16, 16, 16);
			}
			
			anim_target_time = accumulated_time + 250;
		}
		
		if (move_anim_target_time < accumulated_time)
		{
			if (++move_anim_state >= 4) move_anim_state = 0;
			
			move_anim_target_time = accumulated_time + 250;
		}
	}
	
	private boolean moveTo(Point dest)
	{
		if (move_target_time < accumulated_time)
		{
			if (dest.x < location.x && location.x - 1 >= 0)            // ==== HORIZONTAL ==== //
			{
				location.x--;
				direction = 3;
			} else if (dest.x > location.x && location.x + 1 <= layerWidth)
			{
				location.x++;
				direction = 2;
			} else if (dest.y < location.y && location.y - 1 >= 0)        // ===== VERTICAL ===== //
			{
				location.y--;
				direction = 0;
			} else if (dest.y > location.y && location.y + 1 <= layerHeight)
			{
				location.y++;
				direction = 1;
			}
			
			walking = !location.equals(dest);
			
			move_target_time = accumulated_time + 1000;
		}
		
		return location.equals(dest);
	}
	
	public void draw()
	{
		renderer.draw(sprite, location.x * 32, location.y * 32, 32, 32);
	}
	
	public void dispose()
	{
		if (--nbColonists == 0)
			maleNPCTexture.dispose();
	}
}
