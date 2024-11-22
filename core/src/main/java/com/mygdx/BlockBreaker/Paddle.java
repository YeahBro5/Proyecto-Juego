package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Managers.AudioManager;

public class Paddle implements Collidable{
    private int x = 20;
    private int y = 20;
    private int width = 100;
    private int height = 10;

    public Paddle(int x, int y, int ancho, int alto) {
    	this.x = x;
    	this.y= y;
    	width = ancho;
    	height = alto;
    }

    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	public void draw(ShapeRenderer shape) {
        shape.setColor(Color.BLUE);
        int x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 = x-10;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2 = x+10;
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            x = x2;
        }
        shape.rect(x, y, width, height);
    }

    public void onCollision(Collidable object) {
        AudioManager.getInstance().reproducirSonido("pong");
    }
}
