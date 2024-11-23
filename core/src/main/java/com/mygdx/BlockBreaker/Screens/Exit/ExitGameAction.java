package com.mygdx.BlockBreaker.Screens.Exit;


import com.badlogic.gdx.Gdx;
import com.mygdx.BlockBreaker.Screens.MenuAction;

public class ExitGameAction implements MenuAction {
    @Override
    public void execute()
    {
        Gdx.app.exit();
    }
}
