package com.mygdx.BlockBreaker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Block {
    int x,y,width,height;
    Color cc;
    boolean destroyed;

    public void draw(ShapeRenderer shape){
        shape.setColor(cc);
        shape.rect(x, y, width, height);
    }

    public abstract void destroy();
}
