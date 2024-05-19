package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture backgroundTexture;

    public GameOverScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("road.png")); // Ruta a la textura de la carretera sin los coches
        font = new BitmapFont();
        font.getData().setScale(3);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 1080, 1920);
        font.draw(batch, "GAME OVER", 400, 1000);
        font.draw(batch, "JUGAR OTRA VEZ", 350, 800);
        batch.end();

        if (Gdx.input.justTouched()) {
            GameScreen newGameScreen = new GameScreen(camera, game);
            game.setScreen(newGameScreen);
            ((MainGame) game).gameScreen = newGameScreen;
            dispose();
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }
}
