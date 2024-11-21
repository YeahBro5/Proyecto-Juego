package com.mygdx.BlockBreaker.Screens;

import com.mygdx.BlockBreaker.BlockBreakerGame;


public class StartGameAction implements MenuAction {
    private final BlockBreakerGame game;

    public StartGameAction(BlockBreakerGame game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.setScreen(new StartGameScreen(game));
    }
}
