package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private BitmapFont font;

    public PlayScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("road.png")); // Ruta a la textura de la carretera sin los coches
        font = new BitmapFont();
        font.getData().setScale(5); // Tamaño del texto
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

        // Dibujar el fondo
        batch.draw(backgroundTexture, 0, 0, 1080, 1920);

        // Dibujar el texto "CLICK PARA JUGAR" centrado en la pantalla
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "CLICK PARA JUGAR");
        float textX = (1080 - layout.width) / 2;
        float textY = (1920 + layout.height) / 2;
        font.draw(batch, layout, textX, textY);

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
        backgroundTexture.dispose();
        font.dispose();
    }
}
