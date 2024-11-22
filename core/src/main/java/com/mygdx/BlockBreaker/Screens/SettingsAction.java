package com.mygdx.BlockBreaker.Screens;

import com.mygdx.BlockBreaker.BlockBreakerGame;

public class SettingsAction implements MenuAction{

    private final BlockBreakerGame game;

    public SettingsAction(BlockBreakerGame game) {
        this.game = game;
    }

    public void execute()
    {
        //game.setScreen(new SettingsScreen(game));
    }
}
