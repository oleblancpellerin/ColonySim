package net.ddns.djeezuss;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class ColonySim extends ApplicationAdapter
{
	private OrthogonalTiledMapRenderer mapRenderer;
	private SpriteBatch spriteRenderer;
	private TiledMap tiledMap;
	private OrthographicCamera camera;
	private Colonist colonist;

	private long prev_time;
	private long curr_time;

	@Override
	public void create()
	{
		spriteRenderer = new SpriteBatch();

		tiledMap = new TmxMapLoader().load("maps/map.tmx");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());

		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		mapRenderer.getBatch().enableBlending();
		mapRenderer.setView(camera);

		colonist = new Colonist(tiledMap, spriteRenderer);

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
		handle_inputs();

		colonist.update(delta);
	}

	private void draw()
	{
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();

		colonist.draw();
	}

	private void handle_inputs()
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

		if (!transform.isZero())
		{
			camera.translate(transform);
			camera.update();
			mapRenderer.setView(camera);
		}
	}

	@Override
	public void dispose()
	{
		tiledMap.dispose();
		spriteRenderer.dispose();
		mapRenderer.dispose();
		colonist.dispose();
	}
}
