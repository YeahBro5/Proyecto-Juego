package com.mygdx.BlockBreaker.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.BlockBreaker.Collidable;

import java.util.Random;

public class CommonBlock extends Block {

    public CommonBlock(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE);
    }

    @Override
    public void destroy() {this.destroyed = true;}

    // Metodo estático para generar un color aleatorio basado en la posición
    private static Color generateRandomColor(int x, int y) {
        Random r = new Random(x + y);
        return new Color(0.1f + r.nextFloat() * 0.9f, r.nextFloat(), r.nextFloat(), 1f);
    }

    public void onCollision(Collidable other){
        destroy();
    }
}
