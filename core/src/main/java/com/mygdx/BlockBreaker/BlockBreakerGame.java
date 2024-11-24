package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Managers.AudioManager;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Managers.TextureManager;
import com.mygdx.BlockBreaker.Managers.UIManager;
import com.mygdx.BlockBreaker.Screens.MainMenuScreen;
import com.mygdx.BlockBreaker.Screens.MenuAction;
import com.mygdx.BlockBreaker.Screens.StartGame.StartGameAction;

public class BlockBreakerGame extends Game {
    private GameManager gameManager;
    private UIManager uiManager;
    private ShapeRenderer shape;
    MenuAction initialAction;

    @Override
    public void create() {
        cargarAssets();
        this.initialAction = new StartGameAction(this);
        setScreen(new MainMenuScreen(this, initialAction)); // Inicia con el menú principal
    }

    private void cargarAssets() {
        TextureManager gestorTexturas = TextureManager.getInstance();
        AudioManager gestorAudio = AudioManager.getInstance();

        //gestorTexturas.cargarTextura("fondo-1", "Texturas/si1.png");
        gestorTexturas.cargarTextura("fondo-1", "Texturas/Galaxy-Green.png");

        gestorAudio.cargarMusica("musica-1", "Musicas/musica-1.mp3");
        gestorAudio.cargarMusica("musica-2", "Musicas/musica-2.mp3");

        gestorAudio.cargarSonido("pong", "Efectos/pong.mp3");
        gestorAudio.cargarSonido("damage", "Efectos/damage.mp3");
        gestorAudio.cargarSonido("snapping", "Efectos/snapping.mp3");
        gestorAudio.cargarSonido("metal", "Efectos/metal.mp3");
        gestorAudio.cargarSonido("shovel", "Efectos/shovel.mp3");
        gestorAudio.cargarSonido("wood", "Efectos/wood.mp3");
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
