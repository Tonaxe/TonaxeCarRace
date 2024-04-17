package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen {
    private OrthographicCamera camera;
    private Game game;
    private MainGame mainGame; // Agregar una referencia a MainGame

    private PlayerCar playerCar;
    private Road road;
    private List<Car> cars;
    private Random random;

    private static final float TOUCH_LEFT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2; // Umbral de la mitad de la pantalla
    private static final float TOUCH_RIGHT_THRESHOLD = (float) Gdx.graphics.getWidth() / 2;

    private float timeSinceLastCar; // Tiempo transcurrido desde que se agregó el último coche
    private static final float TIME_BETWEEN_CARS = 2.0f; // Tiempo entre la aparición de cada coche


    public GameScreen(OrthographicCamera camera, Game game) {
        this.camera = camera;
        this.game = game; // Almacenar la referencia al juego
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
        road.render(batch); // Utilizamos el método render de Road
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

    public void update(float delta) {
        playerCar.update();
        road.update();
        for (Car car : cars) {
            car.update();
            car.getPosition().y -= 5; // Mover hacia abajo con la misma velocidad que el fondo
        }

        // Incrementar el temporizador
        timeSinceLastCar += delta;

        // Si ha pasado suficiente tiempo, añadir un nuevo coche
        if (timeSinceLastCar >= TIME_BETWEEN_CARS) {
            addRandomCar(); // Agregar un nuevo coche a intervalos regulares
            timeSinceLastCar = 0; // Reiniciar el temporizador
        }

        checkCollisions(); // Comprobar colisiones
    }

    private void addRandomCar() {
        // Generar un número aleatorio para determinar el carril del nuevo coche
        int lane = random.nextInt(3); // 0, 1 o 2

        // Determinar la posición x del nuevo coche según el carril
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

        // Agregar un nuevo coche en la posición determinada
        cars.add(new Car(new Vector2(xPosition, 1920)));
    }

    public void checkCollisions() {
        for (Car car : cars) {
            if (playerCar.collidesWith(car)) {
                // Colisión detectada, cambiar a la pantalla de Game Over
                gameOver();
                break;
            }
        }
    }

    private void gameOver() {
        // Cambiar a la pantalla de Game Over
        game.setScreen(new GameOverScreen(game)); // Suponiendo que `game` es una referencia a tu instancia de `MainGame`
        // Este método debe implementarse de acuerdo a cómo manejes las pantallas en tu juego
    }
}
