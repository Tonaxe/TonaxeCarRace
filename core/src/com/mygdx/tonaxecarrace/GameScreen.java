package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen {
    private OrthographicCamera camera;
    private PlayerCar playerCar;
    private Road road;
    private List<Car> cars;
    private Random random;

    private static final float TOUCH_LEFT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2; // Umbral de la mitad de la pantalla
    private static final float TOUCH_RIGHT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;


    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        camera.setToOrtho(false, 1080, 1920);
        playerCar = new PlayerCar();
        road = new Road();
        cars = new ArrayList<>();
        random = new Random();
    }

    public void render(SpriteBatch batch) {
        playerCar.update();
        road.update();
        for (Car car : cars) {
            car.update();
        }
        batch.draw(road.getTexture(), 0, 0);
        batch.draw(playerCar.getTexture(), playerCar.getPosition().x, playerCar.getPosition().y);
        for (Car car : cars) {
            batch.draw(car.getTexture(), car.getPosition().x, car.getPosition().y);
        }
    }

    public void dispose() {
        playerCar.dispose();
        road.dispose();
        for (Car car : cars) {
            car.dispose();
        }
    }

    public void handleInput(float touchX) {
        // Si el jugador toca la parte izquierda de la pantalla, mueve el coche hacia la izquierda
        if (touchX < TOUCH_LEFT_THRESHOLD) {
            playerCar.moveLeft();
        }
        // Si el jugador toca la parte derecha de la pantalla, mueve el coche hacia la derecha
        else if (touchX > TOUCH_RIGHT_THRESHOLD) {
            playerCar.moveRight();
        }
    }
}

