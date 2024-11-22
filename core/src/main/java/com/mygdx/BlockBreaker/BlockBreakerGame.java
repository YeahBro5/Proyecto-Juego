package com.mygdx.BlockBreaker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Managers.UIManager;
import com.mygdx.BlockBreaker.Screens.MainMenuScreen;

public class BlockBreakerGame extends Game {
    private GameManager gameManager;
    private UIManager uiManager;
    private ShapeRenderer shape;

    @Override
    public void create() {
        //gameManager = GameManager.getInstance();
        //gameManager.initializeLevel(1);
        //uiManager = new UIManager(gameManager);
        //shape = new ShapeRenderer();
        setScreen(new MainMenuScreen(this)); // Inicia con el menú principal
    }

    @Override
    public void render() {
        super.render(); // Se llama al render de la pantalla activa !!
    }

    @Override
    public void dispose() {
        super.dispose(); // Se llama al dispose de la pantalla activa !!
    }
}
