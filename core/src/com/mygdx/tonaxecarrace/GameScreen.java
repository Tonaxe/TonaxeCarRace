package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private Game game;
    private MainGame mainGame;
    private GameOverScreen gameOverScreen;

    private PlayerCar playerCar;
    private Road road;
    private List<Car> cars;
    private Random random;

    private static final float TOUCH_LEFT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;
    private static final float TOUCH_RIGHT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;

    private float timeSinceLastCar;
    private static final float TIME_BETWEEN_CARS = 2.0f;


    public GameScreen(OrthographicCamera camera, Game game) {
        this.camera = camera;
        this.game = game;
        this.mainGame = (MainGame) game;
        //.gameOverScreen = ((MainGame) game).gameOverScreen;
        camera.setToOrtho(false, 1080, 1920);
        playerCar = new PlayerCar();
        road = new Road();
        cars = new ArrayList<>();
        random = new Random();
    }

   /* public void render(SpriteBatch batch) {
        playerCar.update();
        road.update();
        for (Car car : cars) {
            car.update();
        }
        road.render(batch);
        batch.draw(playerCar.getTexture(), playerCar.getPosition().x, playerCar.getPosition().y);
        for (Car car : cars) {
            batch.draw(car.getTexture(), car.getPosition().x, car.getPosition().y);
        }

        for (Car car : cars) {
            if (playerCar.collidesWith(car)) {
                gameOverScreen = new GameOverScreen(game);
                game.setScreen(gameOverScreen);
                dispose();
                break;
            }
        }
    }*/


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        mainGame.batch.setProjectionMatrix(camera.combined);
        playerCar.update();
        road.update();
        for (Car car : cars) {
            car.update();
        }
        mainGame.batch.begin();
        road.render(mainGame.batch);
        mainGame.batch.draw(playerCar.getTexture(), playerCar.getPosition().x, playerCar.getPosition().y);
        for (Car car : cars) {
            mainGame.batch.draw(car.getTexture(), car.getPosition().x, car.getPosition().y);
        }
        mainGame.batch.end();

        for (Car car : cars) {
            if (playerCar.collidesWith(car)) {
                gameOverScreen = new GameOverScreen(game);
                game.setScreen(gameOverScreen);
                dispose();
                break;
            }
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

    public void dispose() {
        playerCar.dispose();
        road.dispose();
        for (Car car : cars) {
            car.dispose();
        }
    }

    public void handleInput(float touchX) {
        if (touchX < TOUCH_LEFT_THRESHOLD) {
            playerCar.moveLeft();
        }
        else if (touchX > TOUCH_RIGHT_THRESHOLD) {
            playerCar.moveRight();
        }
    }

    public void update(float delta) {
        playerCar.update();
        road.update();
        for (Car car : cars) {
            car.update();
            car.getPosition().y -= 5;
        }
        timeSinceLastCar += delta;

        if (timeSinceLastCar >= TIME_BETWEEN_CARS) {
            addRandomCar();
            timeSinceLastCar = 0;
        }
    }

    private void addRandomCar() {
        int lane = random.nextInt(3); // 0, 1 o 2
        float xPosition = 0;
        switch (lane) {
            case 0:
                xPosition = 341;
                break;
            case 1:
                xPosition = 492;
                break;
            case 2:
                xPosition = 644;
                break;
        }
        cars.add(new Car(new Vector2(xPosition, 1920)));
    }
}
