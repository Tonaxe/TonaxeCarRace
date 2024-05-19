package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerCar {
    private static final String TEXTURE_PATH = "player_car.png";
    private static final float SPEED = 10;
    private Texture texture;
    private Vector2 position;
    private float screenWidth;


    public PlayerCar() {
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
        screenWidth = Gdx.graphics.getWidth();
        position = new Vector2(495, 200);
    }

    public void update() {
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void moveLeft() {
        float newPositionX = position.x - SPEED;
        if (newPositionX >= 320) {
            position.x = Math.max(newPositionX, 320);
        }
    }

    public void moveRight() {
        float newPositionX = position.x + SPEED;
        if (newPositionX + texture.getWidth() <= 770) {
            position.x = Math.min(newPositionX, 770 - texture.getWidth());
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean collidesWith(Car car) {
        float playerLeft = position.x;
        float playerRight = position.x + texture.getWidth();
        float playerTop = position.y + texture.getHeight();
        float playerBottom = position.y;

        float carLeft = car.getPosition().x;
        float carRight = car.getPosition().x + car.getTexture().getWidth();
        float carTop = car.getPosition().y + car.getTexture().getHeight();
        float carBottom = car.getPosition().y;

        if (playerRight >= carLeft && playerLeft <= carRight && playerTop >= carBottom && playerBottom <= carTop) {
            return true;
        }
        return false;
    }

}