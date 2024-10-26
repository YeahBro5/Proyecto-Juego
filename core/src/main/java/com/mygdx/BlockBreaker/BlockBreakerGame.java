///*
package com.mygdx.BlockBreaker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Managers.UIManager;

public class BlockBreakerGame extends ApplicationAdapter {
    private GameManager gameManager;
    private UIManager uiManager;
    private ShapeRenderer shape;

    @Override
    public void create() {
        gameManager = GameManager.getInstance();
        gameManager.initializeLevel(1);
        uiManager = new UIManager(gameManager);
        shape = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameManager.updateGameState();
        gameManager.checkCollisions();

        // Dibujar elementos del juego
        gameManager.drawGameObjects(shape);

        // Dibujar interfaz de usuario
        uiManager.renderUI();
    }

    @Override
    public void dispose() {
        shape.dispose();
        gameManager.dispose();
        uiManager.dispose();
    }
}
