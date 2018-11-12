package net.ddns.djeezuss;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import net.ddns.djeezuss.pathfinding.PathFindingAStar;
import net.ddns.djeezuss.pathfinding.TiledLayerAdapter;

public class ColonySim extends ApplicationAdapter
{
	private boolean[][] matrix;
	private OrthogonalTiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteRenderer;
	private TiledMap tiledMap;
	private OrthographicCamera camera;
	private Colonist colonist;
	private long prev_time;
	private long curr_time;

	@Override
	public void create()
	{
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		spriteRenderer = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		tiledMap = new TmxMapLoader().load("maps/map.tmx");
		TiledMapTileLayer tiledLayer = ((TiledMapTileLayer) tiledMap.getLayers().get("Collision"));

		matrix = TiledLayerAdapter.getBooleanMatrix(tiledLayer);

		/*StringBuilder stringBuilder = new StringBuilder().append("\n");
		for (int i = ((TiledMapTileLayer) tiledMap.getLayers().get("Collision")).getHeight() - 1; i >= 0; i--)
		{
			stringBuilder.append("[");
			for (int j = 0; j < ((TiledMapTileLayer) tiledMap.getLayers().get("Collision")).getWidth(); j++)
			{
				stringBuilder.append((matrix[j][i]) ? "." : 0).append(" ");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("]\n");
		}
		Gdx.app.debug(getClass().getName(), stringBuilder.toString());*/
		//PathFindingAStar.generateHValue(matrix, 22, 42, 37, 54, tiledLayer.getWidth(), tiledLayer.getHeight(), 5);

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
		handle_inputs();

		colonist.update(delta);
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
		if (Gdx.app.getInput().isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

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
		super.dispose();

		tiledMap.dispose();
		spriteRenderer.dispose();
		mapRenderer.dispose();
		colonist.dispose();
	}
}
