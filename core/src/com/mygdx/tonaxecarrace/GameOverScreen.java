package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    public GameOverScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3); // Escala del texto
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar el texto de "Game Over" centrado en la pantalla
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Game Over", 400, 1000);
        font.draw(batch, "Tap to Play Again", 300, 800);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resume() {
        batch = new SpriteBatch();
    }

    @Override
    public void pause() {
        batch.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }
}
