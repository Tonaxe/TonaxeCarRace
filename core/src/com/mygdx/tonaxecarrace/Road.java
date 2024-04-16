package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Road {
    private static final String TEXTURE_PATH = "road.png";
    private Texture texture;
    private TextureRegion textureRegion;
    private float yPosition; // Posición vertical de la carretera


    public Road() {
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) texture.getWidth();
        int newHeight = (int) (texture.getHeight() * aspectRatio);
        textureRegion = new TextureRegion(texture, 0, 0, Gdx.graphics.getWidth(), newHeight);
        yPosition = Gdx.graphics.getHeight();
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void update() {
        // Mover la carretera hacia abajo
        yPosition -= 5; // Puedes ajustar la velocidad de desplazamiento según sea necesario

        // Si la carretera se ha movido fuera de la pantalla, reposicionarla en la parte superior
        if (yPosition <= -1920) { // La altura de la pantalla es 1920px
            yPosition = 1920; // Reposicionar en la parte superior
        }

        // Actualizar la región de textura con la nueva posición vertical
        textureRegion.setRegion(0, (int) yPosition, 1080, 1920);

        // Si la textura ha alcanzado el borde superior de la pantalla, repetirla arriba
        if (yPosition + 1920 <= 0) {
            yPosition += 1920;
        }
    }
}