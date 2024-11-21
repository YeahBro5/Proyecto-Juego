package com.mygdx.BlockBreaker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.BlockBreakerGame;
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

        // Dibujar elementos del juego
        gameManager.drawGameObjects(shape);

        // Dibujar interfaz de usuario
        uiManager.renderUI();

        // Actualizar y renderizar el juego

        //shape.begin(ShapeRenderer.ShapeType.Filled);
        //shape.circle(100, 100, 50); // Ejemplo de renderizado
        //shape.end();
    }

    private void renderPauseMenu() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("Menú de Pausa");
        // Aquí podrías integrar un menú de pausa con botones.
    }

    @Override
    public void dispose() {
        shape.dispose();
        gameManager.dispose();
        uiManager.dispose();
    }
}

