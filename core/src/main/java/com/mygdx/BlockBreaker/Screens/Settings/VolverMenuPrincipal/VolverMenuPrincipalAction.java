package com.mygdx.BlockBreaker.Screens.Settings.VolverMenuPrincipal;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Screens.MainMenuScreen;
import com.mygdx.BlockBreaker.Screens.MenuAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VolverMenuPrincipalAction implements MenuAction {
    private final BlockBreakerGame game;
    boolean pauseMenu;

    public VolverMenuPrincipalAction(BlockBreakerGame game) {
        this.game = game;
        this.pauseMenu = false;
    }
    public VolverMenuPrincipalAction(BlockBreakerGame game, boolean pauseMenu) {
        this.game = game;
        this.pauseMenu = pauseMenu;
    }

    public void execute()
    {
        if (pauseMenu)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Guardado/Guardado.csv"))) {
                GameManager gameManager = GameManager.getInstance();
                int nivel = gameManager.getLevel();
                int score = gameManager.getScore();
                int lives = gameManager.getLives();
                writer.write(nivel+ ";" + score + ";" + lives);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        game.setScreen(new MainMenuScreen(game));
    }
}
