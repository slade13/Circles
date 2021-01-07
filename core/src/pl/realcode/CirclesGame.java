package pl.realcode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;

public class CirclesGame extends ApplicationAdapter {
    private ShapeRenderer renderer;
    private ArrayList<Circle> circles;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        circles = new ArrayList<>();
    }

    @Override
    public void render() {
        handleInput();
        handleRendering();
    }

    private void handleRendering() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1f, 1f, 1f, 0.7f);

        int size = circles.size();
        float delta = Gdx.graphics.getDeltaTime();
        Iterator<Circle> iterator = circles.iterator();
        for(int i = 0; i < size; i++){
            iterator.next().render(renderer, delta);
        }

        renderer.end();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Constants.HEIGHT - Gdx.input.getY();
            int radius = Constants.radius;
            circles.add(new Circle(x, y, radius));
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
        circles.clear();
    }
}
