package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
public class MainGame extends Game {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameScreen gameScreen;

    @Override
    public void create() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.setToOrtho(false);
        camera.setToOrtho(false, 1080, 1920);
        batch = new SpriteBatch();
        gameScreen = new GameScreen(camera, this); // Pasar la referencia al juego
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        gameScreen.render(batch); // Asegúrate de llamar al método render de GameScreen
        batch.end();
        handleInput(); // Manejar la entrada del usuario
        float delta = Gdx.graphics.getDeltaTime(); // Obtener el tiempo transcurrido desde el último fotograma
        gameScreen.update(delta); // Llamar al método update de GameScreen con el delta
        gameScreen.checkCollisions(); // Verificar las colisiones después de actualizar
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            gameScreen.handleInput(touchX); // Pasar la posición del toque a GameScreen
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameScreen.dispose();
    }
}
