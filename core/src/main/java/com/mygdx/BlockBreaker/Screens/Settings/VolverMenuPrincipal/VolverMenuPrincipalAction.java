package com.mygdx.BlockBreaker.Screens.Settings.VolverMenuPrincipal;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MainMenuScreen;
import com.mygdx.BlockBreaker.Screens.MenuAction;

public class VolverMenuPrincipalAction implements MenuAction {
    private final BlockBreakerGame game;

    public VolverMenuPrincipalAction(BlockBreakerGame game) {
        this.game = game;
    }

    public void execute()
    {
        game.setScreen(new MainMenuScreen(game));
    }
}
