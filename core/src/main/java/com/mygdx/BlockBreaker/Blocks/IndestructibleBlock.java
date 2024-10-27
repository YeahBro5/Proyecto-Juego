package com.mygdx.BlockBreaker.Blocks;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class IndestructibleBlock extends Block {

    public IndestructibleBlock(int x, int y, int width, int height) {
        super(x, y, width, height, generateRandomColor(x, y));
    }

    @Override
    public void destroy() {this.destroyed = false;}

    // Metodo estático para generar un color aleatorio basado en la posición
    private static Color generateRandomColor(int x, int y) {
        Random r = new Random(x + y);
        return new Color(0.1f + r.nextFloat() * 0.9f, r.nextFloat(), r.nextFloat(), 1f);
    }
    public void onCollision(Object other){
        destroy();
    }
}
