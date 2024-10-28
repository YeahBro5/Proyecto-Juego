package com.mygdx.BlockBreaker.Managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

public class UIManager {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GameManager gameManager;

    public UIManager(GameManager gameManager) {
        this.gameManager = gameManager;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2); // Escala de la fuente para mejor visibilidad
    }

    public void renderUI() {
        batch.begin();

        // Dibujar el puntaje, nivel y vidas
        font.draw(batch, "Puntos: " + gameManager.getScore(), 10, 25);
        font.draw(batch, "Nivel: " + gameManager.getLevel(), (float) Gdx.graphics.getWidth() / 2 - 40, 25);
        font.draw(batch, "Vidas: " + gameManager.getLives(), Gdx.graphics.getWidth() - 120, 25);

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
