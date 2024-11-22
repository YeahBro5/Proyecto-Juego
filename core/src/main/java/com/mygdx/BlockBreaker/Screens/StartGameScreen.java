package com.mygdx.BlockBreaker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Managers.AudioManager;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Managers.UIManager;

public class StartGameScreen extends ScreenAdapter {
    private GameManager gameManager;
    private UIManager uiManager;
    private final BlockBreakerGame game;
    private boolean paused = false;
    private ShapeRenderer shape;

    public StartGameScreen(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        gameManager = GameManager.getInstance();
        gameManager.initializeLevel(1);
        uiManager = new UIManager(gameManager);
        shape = new ShapeRenderer();
        AudioManager.getInstance().reproducirMusica("musica-1", true);
    }

    @Override
    public void render(float delta) {
        // Pausar el juego con Escape
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
        }

        if (paused) {
            renderPauseMenu();
            return;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameManager.updateGameState();
        gameManager.checkCollisions();

        // Dibujar interfaz de usuario
        uiManager.renderUI();

        // Dibujar elementos del juego
        gameManager.drawGameObjects(shape);
    }

    private void renderPauseMenu() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("Menú de Pausa");
    }

    @Override
    public void dispose() {
        shape.dispose();
        gameManager.dispose();
        uiManager.dispose();
    }
}

