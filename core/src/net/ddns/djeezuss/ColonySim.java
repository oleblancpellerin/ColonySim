package net.ddns.djeezuss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class ColonySim extends ApplicationAdapter
{
	private OrthoCachedTiledMapRenderer mapRenderer;
	private SpriteBatch renderer;
	private TiledMap tiledMap;
	private OrthographicCamera camera;

	@Override
	public void create()
	{
		renderer = new SpriteBatch();

		tiledMap = new TmxMapLoader().load("maps/map.tmx");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		mapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		mapRenderer.setView(camera);
		mapRenderer.setBlending(true);
	}

	@Override
	public void render()
	{
		update();
		draw();
	}

	private void update()
	{
		Vector2 transform = new Vector2(0, 0);

		if (Gdx.app.getInput().isKeyPressed(Input.Keys.UP))
			transform.add(0, 5);
		if (Gdx.app.getInput().isKeyPressed(Input.Keys.DOWN))
			transform.add(0, -5);
		if (Gdx.app.getInput().isKeyPressed(Input.Keys.LEFT))
			transform.add(-5, 0);
		if (Gdx.app.getInput().isKeyPressed(Input.Keys.RIGHT))
			transform.add(5, 0);

		camera.translate(transform);
		camera.update();
		mapRenderer.setView(camera);
	}

	private void draw()
	{
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();
	}

	@Override
	public void dispose()
	{
		tiledMap.dispose();
		renderer.dispose();
		mapRenderer.dispose();
	}
}
