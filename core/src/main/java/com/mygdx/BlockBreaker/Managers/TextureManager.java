package com.mygdx.BlockBreaker.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private static TextureManager instance;

    private final Map<String, Texture> texturas;

    private TextureManager() {
        texturas = new HashMap<>();
    }

    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }

    public void cargarTextura(String key, String path) {
        Texture textura = new Texture(Gdx.files.internal(path));
        texturas.put(key, textura);
    }

    public Texture getTextura(String key) {
        return texturas.get(key);
    }

    public void dispose() {
        for (Texture textura: texturas.values()) {
            textura.dispose();
        }
        texturas.clear();
    }
}
