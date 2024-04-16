package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Road {
    private static final String TEXTURE_PATH = "road.png";
    private Texture texture;
    private TextureRegion[] textureRegions; // Array para mantener múltiples regiones de textura
    private float[] yPositions; // Array para mantener las posiciones verticales de las regiones
    private int numRoads; // Número de instancias de la carretera


    public Road() {
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
        int screenHeight = Gdx.graphics.getHeight();
        numRoads = (screenHeight / texture.getHeight()) + 2; // Calculamos el número necesario de instancias de la carretera
        textureRegions = new TextureRegion[numRoads];
        yPositions = new float[numRoads];

        // Inicializamos las instancias de la carretera y sus posiciones
        for (int i = 0; i < numRoads; i++) {
            textureRegions[i] = new TextureRegion(texture);
            yPositions[i] = i * texture.getHeight(); // Posición inicial
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void update() {
        for (int i = 0; i < numRoads; i++) {
            yPositions[i] -= 5; // Desplazar hacia arriba

            // Si la carretera se ha movido fuera de la pantalla, reposicionarla encima de la última
            if (yPositions[i] <= -texture.getHeight()) {
                yPositions[i] = yPositions[(i + numRoads - 1) % numRoads] + texture.getHeight();
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < numRoads; i++) {
            batch.draw(texture, 0, yPositions[i]);
            if (i == 0) {
                batch.draw(texture, 0, yPositions[numRoads - 1] + texture.getHeight());
            }
        }
    }
}