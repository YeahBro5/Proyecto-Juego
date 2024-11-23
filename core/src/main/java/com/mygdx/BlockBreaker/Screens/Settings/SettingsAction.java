package com.mygdx.BlockBreaker.Screens.Settings;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MenuAction;

public class SettingsAction implements MenuAction {

    private final BlockBreakerGame game;

    public SettingsAction(BlockBreakerGame game) {
        this.game = game;
    }

    public void execute()
    {
        game.setScreen(new SettingsScreen(game));
    }
}
