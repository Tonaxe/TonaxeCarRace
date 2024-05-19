package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture playButtonTexture;
    private float buttonX, buttonY;

    public PlayScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        playButtonTexture = new Texture(Gdx.files.internal("play_button.png")); // Usa la ruta correcta para tu botón de play
        buttonX = (1080 - playButtonTexture.getWidth()) / 2;
        buttonY = (1920 - playButtonTexture.getHeight()) / 2;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playButtonTexture, buttonX, buttonY);
        batch.end();

        if (Gdx.input.justTouched()) {
            GameScreen newGameScreen = new GameScreen(camera, game);
            game.setScreen(newGameScreen);
            ((MainGame) game).gameScreen = newGameScreen;
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        playButtonTexture.dispose();
    }
}
