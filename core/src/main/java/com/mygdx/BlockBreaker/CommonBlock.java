package com.mygdx.BlockBreaker;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;

public class CommonBlock extends Block {

    public CommonBlock(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        Random r = new Random(x+y);

       cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);

    }
    public void destroy() {
        destroyed = true;
    }
}
