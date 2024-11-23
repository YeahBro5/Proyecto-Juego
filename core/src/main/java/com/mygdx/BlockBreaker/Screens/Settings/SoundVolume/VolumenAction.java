package com.mygdx.BlockBreaker.Screens.Settings.SoundVolume;

import com.mygdx.BlockBreaker.BlockBreakerGame;
import com.mygdx.BlockBreaker.Screens.MenuAction;

public class VolumenAction implements MenuAction {
    private final BlockBreakerGame game;

    public VolumenAction(BlockBreakerGame game) {
        this.game = game;
    }

    public void execute()
    {
        game.setScreen(new VolumenScreen(game));
    }
}
