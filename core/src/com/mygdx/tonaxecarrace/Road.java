package com.mygdx.tonaxecarrace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Road {
    private static final String TEXTURE_PATH = "road.png";
    private Texture texture;
    private TextureRegion textureRegion;

    public Road() {
        texture = new Texture(Gdx.files.internal(TEXTURE_PATH));
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) texture.getWidth();
        int newHeight = (int) (texture.getHeight() * aspectRatio);
        textureRegion = new TextureRegion(texture, 0, 0, Gdx.graphics.getWidth(), newHeight);
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
        // Implementa la lógica de actualización de la carretera aquí si es necesario
    }
}
