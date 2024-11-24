package com.mygdx.BlockBreaker.Blocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Collidable;
import com.mygdx.BlockBreaker.GameObject;

public abstract class Block extends GameObject implements Collidable {
    protected Color color;
    protected boolean destroyed;

    public Block(int x, int y, int ancho, int alto, Color color) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        this.color = color != null ? color : Color.WHITE; // Color por defecto si no se especifica
        this.destroyed = false;
    }

    public void draw(ShapeRenderer shape) {
        if (!destroyed) {
            shape.setColor(color);
            shape.rect(posX, posY, width, height);
        }
    }

    // Clase Abstracta
    public abstract void destroy();
    public abstract void onCollision(Collidable other);

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public boolean isDestroyed() { return destroyed; }
    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }
}
