package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PowerUp {
    public static final String TEXTURE_PATH = "powerup.png";
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    private Texture texture;
    private Vector2 position;
    private static final int SPEED = 5;

    public PowerUp(Vector2 position) {
        this.position = position;
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
    }

    public void update() {
        position.y -= SPEED;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean collidesWith(PlayerCar playerCar) {
        float playerLeft = playerCar.getPosition().x;
        float playerRight = playerCar.getPosition().x + playerCar.getTexture().getWidth();
        float playerTop = playerCar.getPosition().y + playerCar.getTexture().getHeight();
        float playerBottom = playerCar.getPosition().y;

        float powerUpLeft = position.x;
        float powerUpRight = position.x + texture.getWidth();
        float powerUpTop = position.y + texture.getHeight();
        float powerUpBottom = position.y;

        return playerRight >= powerUpLeft && playerLeft <= powerUpRight && playerTop >= powerUpBottom && playerBottom <= powerUpTop;
    }
}