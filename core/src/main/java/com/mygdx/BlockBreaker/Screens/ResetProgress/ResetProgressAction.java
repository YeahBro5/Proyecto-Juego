package com.mygdx.BlockBreaker.Screens.ResetProgress;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MenuAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResetProgressAction implements MenuAction {
    private final BlockBreakerGame game;

    public ResetProgressAction(BlockBreakerGame game) {
        this.game = game;
    }

    public void execute()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("assets/Guardado/Guardado.csv"))) {

            writer.write("1;0;3");




        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
