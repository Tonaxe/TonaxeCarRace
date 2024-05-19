package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
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
    private int score;
    private BitmapFont font;
    private SpriteBatch batch;

    private static final float TOUCH_LEFT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;
    private static final float TOUCH_RIGHT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;

    private float timeSinceLastCar;
    private static final float TIME_BETWEEN_CARS = 2.0f;

    private float timeSinceLastPowerUp;
    private static final float TIME_BETWEEN_POWERUPS = 10.0f;

    private boolean activado = false;
    private float timeSinceLastPowerUpCollected;
    private float carSpeed;

    private List<PowerUp> powerUps;

    public GameScreen(OrthographicCamera camera, Game game) {
        this.camera = camera;
        this.game = game;
        this.mainGame = (MainGame) game;
        camera.setToOrtho(false, 1080, 1920);
        playerCar = new PlayerCar();
        road = new Road();
        cars = new ArrayList<>();
        powerUps = new ArrayList<>();
        random = new Random();
        score = 0;
        font = new BitmapFont();
        font.getData().setScale(3);
        batch = new SpriteBatch();
        carSpeed = 5;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        mainGame.batch.setProjectionMatrix(camera.combined);
        playerCar.update();
        road.update();
        mainGame.batch.begin();
        drawScore();
        for (Car car : cars) {
            car.update();
        }
        road.render(mainGame.batch);
        mainGame.batch.draw(playerCar.getTexture(), playerCar.getPosition().x, playerCar.getPosition().y);
        for (Car car : cars) {
            mainGame.batch.draw(car.getTexture(), car.getPosition().x, car.getPosition().y);
        }
        for (PowerUp powerUp : powerUps) {
            mainGame.batch.draw(powerUp.getTexture(), powerUp.getPosition().x, powerUp.getPosition().y);
        }
        drawScore();
        mainGame.batch.end();

        for (Car car : cars) {
            if (playerCar.collidesWith(car)) {
                gameOverScreen = new GameOverScreen(game);
                game.setScreen(gameOverScreen);
                dispose();
                break;
            }
        }

        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            if (powerUp.collidesWith(playerCar)) {
                activado = true;
                timeSinceLastPowerUpCollected = 0;
                powerUpIterator.remove();
            }
        }
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    private void drawScore() {
        font.draw(mainGame.batch, "Score: " + score, 50, 1900);
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
        for (PowerUp powerUp : powerUps) {
            powerUp.dispose();
        }
    }

    public void handleInput(float touchX) {
        if (touchX < TOUCH_LEFT_THRESHOLD) {
            playerCar.moveLeft();
        } else if (touchX > TOUCH_RIGHT_THRESHOLD) {
            playerCar.moveRight();
        }
    }

    public void update(float delta) {
        playerCar.update();
        road.update();

        // Incrementa el tiempo desde el último power-up recogido
        if (activado) {
            timeSinceLastPowerUpCollected += delta;
            if (timeSinceLastPowerUpCollected >= 3) {
                activado = false;  // Desactiva después de 3 segundos sin recoger un power-up
            }
        }

        // Itera sobre los coches y actualiza su posición
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            car.update();
            if (activado) {
                car.getPosition().y -= 3;
            } else {
                car.getPosition().y -= 5;
            }

            // Elimina el coche si ha salido completamente de la pantalla
            if (car.getPosition().y + Car.HEIGHT < -100) {
                iterator.remove();
                score++;
            }
        }

        // Itera sobre los power-ups y actualiza su posición
        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            powerUp.update();

            if (powerUp.getPosition().y + PowerUp.HEIGHT < -100) {
                powerUpIterator.remove();
            }
        }

        // Genera nuevos coches si es el momento
        timeSinceLastCar += delta;
        if (timeSinceLastCar >= TIME_BETWEEN_CARS) {
            addRandomCar();
            timeSinceLastCar = 0;
        }

        // Genera nuevos power-ups si es el momento
        timeSinceLastPowerUp += delta;
        if (timeSinceLastPowerUp >= TIME_BETWEEN_POWERUPS) {
            addRandomPowerUp();
            timeSinceLastPowerUp = 0;
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

    private void addRandomPowerUp() {
        int lane = random.nextInt(3);
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
        powerUps.add(new PowerUp(new Vector2(xPosition, 1920)));
    }
}

