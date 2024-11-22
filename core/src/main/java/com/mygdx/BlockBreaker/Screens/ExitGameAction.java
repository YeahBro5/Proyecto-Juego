package com.mygdx.BlockBreaker.Screens;


import com.badlogic.gdx.Gdx;

public class ExitGameAction implements MenuAction {
    @Override
    public void execute()
    {
        Gdx.app.exit();
    }
}
