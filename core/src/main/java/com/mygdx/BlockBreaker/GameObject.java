package com.mygdx.BlockBreaker;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.BlockBreaker.Managers.TextureManager;

public abstract class GameObject {
    protected int posX, posY;
    protected int width, height;
    protected Texture textura;
    protected String efecto;

    public void setPosicion(int x, int y) {
        posX = x;
        posY = y;
    }

    public void setDimensiones(int ancho, int alto) {
        this.width = ancho;
        this.height = alto;
    }

    public int getX() {
        return posX;
    }
    public void setX(int posX) {
        this.posX = posX;
    }
    public int getY() {
        return posY;
    }
    public void setY(int posY) {
        this.posY = posY;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int ancho) {
        this.width = ancho;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int alto) {
        this.height = alto;
    }
    public String getSfx() {
        return efecto;
    }
    public void setSfx(String nombreSfx) {
        efecto = nombreSfx;
    }

    public void setTextura(String nombreTextura) {
        TextureManager gestorTexturas = TextureManager.getInstance();
        textura = gestorTexturas.getTextura(nombreTextura);
    }

    public void draw(SpriteBatch batch) { batch.draw(textura, posX, posY, width, height); }
    public void update() {}

}
