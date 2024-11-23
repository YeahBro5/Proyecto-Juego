package com.mygdx.BlockBreaker.Screens.StartGame;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MenuAction;


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
