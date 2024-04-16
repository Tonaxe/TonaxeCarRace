package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Car {
    public static final String TEXTURE_PATH = "car.png";
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    private Texture texture;
    private Vector2 position;
    private static final int SPEED = 5;

    public Car(Vector2 position) {
        this.position = position;
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
    }

    public void update() {
        // Implementa la lógica de movimiento de los coches aquí
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
}

