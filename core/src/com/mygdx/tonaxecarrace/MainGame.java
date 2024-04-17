package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
public class MainGame extends Game {
    private OrthographicCamera camera;
    public SpriteBatch batch;
    private GameScreen gameScreen;
    //GameOverScreen gameOverScreen;

    @Override
    public void create() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.setToOrtho(false);
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        gameScreen = new GameScreen(camera, this);
        setScreen(gameScreen);
        //gameOverScreen = new GameOverScreen(this);
    }

    @Override
    public void render() {
        super.render();
        handleInput();
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gameScreen.render(batch);
        batch.end();
        handleInput();
        float delta = Gdx.graphics.getDeltaTime();
        gameScreen.update(delta);*/
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            gameScreen.handleInput(touchX);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameScreen.dispose();
    }
}
