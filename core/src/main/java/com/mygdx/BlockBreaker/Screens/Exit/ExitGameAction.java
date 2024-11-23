package com.mygdx.BlockBreaker.Screens.Exit;


import com.badlogic.gdx.Gdx;
import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Managers.GameManager;
import com.mygdx.BlockBreaker.Screens.MenuAction;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExitGameAction implements MenuAction {
    private final BlockBreakerGame game;

    public ExitGameAction(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void execute()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Guardado/Guardado.csv"))) {
            GameManager gameManager = GameManager.getInstance();
            int nivel = gameManager.getLevel();
            int score = gameManager.getScore();
            writer.write(nivel+ ";" + score);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gdx.app.exit();
    }
}
