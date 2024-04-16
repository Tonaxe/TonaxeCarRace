package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private static final String TEXTURE_PATH = "road.png";
    private Texture texture;
    private TextureRegion[] textureRegions; // Array para mantener múltiples regiones de textura
    private float[] yPositions; // Array para mantener las posiciones verticales de las regiones
    private int numRoads; // Número de instancias de la carretera
    private List<Integer> completedRoads;



    public Road() {
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
        int screenHeight = Gdx.graphics.getHeight();
        numRoads = (screenHeight / texture.getHeight()) + 2; // Calculamos el número necesario de instancias de la carretera
        textureRegions = new TextureRegion[numRoads];
        yPositions = new float[numRoads];
        completedRoads = new ArrayList<>();


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
        // Mover las instancias de la carretera hacia abajo
        for (int i = 0; i < numRoads; i++) {
            yPositions[i] -= 5; // Velocidad de desplazamiento ajustable

            // Si la carretera completa ha pasado por debajo de la pantalla, marcarla como completada
            if (yPositions[i] <= -texture.getHeight()) {
                completedRoads.add(i);
            }
        }

        // Eliminar las instancias de la carretera completadas
        for (int completedRoadIndex : completedRoads) {
            yPositions[completedRoadIndex] = yPositions[(completedRoadIndex + numRoads - 1) % numRoads] + texture.getHeight();
        }
        completedRoads.clear(); // Limpiar la lista de instancias de carretera completadas
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