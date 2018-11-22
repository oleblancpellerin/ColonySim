package net.ddns.djeezuss;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import net.ddns.djeezuss.exceptions.PathNotFoundException;
import net.ddns.djeezuss.pathfinding.Node;
import net.ddns.djeezuss.pathfinding.PathFindingAStar;
import net.ddns.djeezuss.pathfinding.TiledLayerAdapter;

import java.util.Arrays;
import java.util.Stack;

public class ColonySim extends ApplicationAdapter implements InputProcessor
{
	private OrthogonalTiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteRenderer;
	private TiledMap tiledMap;
	private OrthographicCamera camera;
	private Colonist colonist;
	
	private long prev_time;
	private long curr_time;
	
	private int mapWidth_px, mapHeight_px;
	
	private boolean[] cameraMovement;
	
	private boolean path_toggle = false;
	
	@Override
	public void create()
	{
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(this);
		Gdx.graphics.setWindowedMode(1280, 720);
		//Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		
		cameraMovement = new boolean[]{false, false, false, false}; // up, down, right, left
		
		spriteRenderer = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		tiledMap = new TmxMapLoader().load("maps/map.tmx");
		TiledMapTileLayer tiledLayer = ((TiledMapTileLayer) tiledMap.getLayers().get("Collision"));
		
		mapWidth_px = tiledLayer.getWidth() * (int) tiledLayer.getTileWidth();
		mapHeight_px = tiledLayer.getHeight() * (int) tiledLayer.getTileHeight();
		
		boolean[][] matrix = TiledLayerAdapter.getBooleanMatrix(tiledLayer);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
		
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		mapRenderer.getBatch().enableBlending();
		mapRenderer.setView(camera);
		
		Colonist.renderer = spriteRenderer;
		Colonist.collisionLayer = matrix;
		colonist = new Colonist(tiledLayer.getWidth(), tiledLayer.getHeight());
		
		prev_time = curr_time = System.currentTimeMillis();
	}
	
	@Override
	public void render()
	{
		prev_time = curr_time;
		curr_time = System.currentTimeMillis();
		
		update(curr_time - prev_time);
		draw();
	}
	
	private void update(long delta)
	{
		colonist.update(delta);
		
		if (cameraMovement[0] || cameraMovement[1] || cameraMovement[2] || cameraMovement[3])
		{
			camera.translate(cameraMovement[2] ? 5 : (cameraMovement[3] ? -5 : 0),
					cameraMovement[0] ? 5 : (cameraMovement[1] ? -5 : 0), 0);
			
			if (camera.position.x + camera.viewportWidth / 2 > mapWidth_px)
				camera.position.x = mapWidth_px - camera.viewportWidth / 2;
			else if (camera.position.x - camera.viewportWidth / 2 < 0)
				camera.position.x = camera.viewportWidth / 2;
			
			if (camera.position.y + camera.viewportHeight / 2 > mapHeight_px)
				camera.position.y = mapHeight_px - camera.viewportHeight / 2;
			else if (camera.position.y - camera.viewportHeight / 2 < 0)
				camera.position.y = camera.viewportHeight / 2;
			
			camera.update();
			mapRenderer.setView(camera);
		}
	}
	
	private void draw()
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mapRenderer.render();
		
		spriteRenderer.setProjectionMatrix(camera.combined);
		spriteRenderer.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		
		colonist.draw();
		
		spriteRenderer.end();
		shapeRenderer.end();
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		
		tiledMap.dispose();
		spriteRenderer.dispose();
		mapRenderer.dispose();
		colonist.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		boolean changed = false;
		
		if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
			cameraMovement[0] = true;
		if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
			cameraMovement[1] = true;
		if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
			cameraMovement[2] = true;
		if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
			cameraMovement[3] = true;
		
		if (keycode == Input.Keys.ESCAPE)
			Gdx.app.exit();
		
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
			cameraMovement[0] = false;
		if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
			cameraMovement[1] = false;
		if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
			cameraMovement[2] = false;
		if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
			cameraMovement[3] = false;
		
		return false;
	}
	
	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (button == Input.Buttons.LEFT)
		{
			Vector3 vec = camera.unproject(new Vector3(screenX, screenY, 0));
			Gdx.app.debug("Camera unproject", "(" + (int) (vec.x / 32) + "," + (int) (vec.y / 32) + ")");
		}
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}
	
	@Override
	public boolean scrolled(int amount)
	{
		if (camera.zoom + amount > 0f)
			camera.zoom += amount;
		
		Gdx.app.debug("Camera zoom", String.valueOf(camera.zoom));
		
		camera.update();
		mapRenderer.setView(camera);
		
		return false;
	}
}
