package com.mygdx.BlockBreaker.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.BlockBreaker.Collidable;
import com.mygdx.BlockBreaker.Managers.AudioManager;

import java.util.Random;

public class IndestructibleBlock extends Block {

    public IndestructibleBlock(int x, int y, int width, int height) {
        super(x, y, width, height, Color.GRAY);
    }

    @Override
    public void destroy() {this.destroyed = true;}

    public void onCollision(Collidable other) {
        //AudioManager.getInstance().reproducirSonido("metal");
        AudioManager.getInstance().reproducirSonido("shovel");
    }
}
