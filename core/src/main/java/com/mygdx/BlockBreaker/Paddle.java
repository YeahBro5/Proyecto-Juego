package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Managers.AudioManager;

public class Paddle extends GameObject implements Collidable{

    public Paddle(int x, int y, int ancho, int alto) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
    }

	public void draw(ShapeRenderer shape) {
        shape.setColor(Color.BLUE);
        int x2 = posX; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 = posX-10;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2 = posX+10;
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            posX = x2;
        }
        shape.rect(posX, posY, width, height);
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float velocidad = 1000f;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            posX -= (int) (velocidad * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            posX += (int) (velocidad * delta);
        }

        // Mantener la barra dentro de la pantalla
        if (posX < 0) {
            setPosicion(0, posY);
        }
        if (posX + width > Gdx.graphics.getWidth()) {
            setPosicion(Gdx.graphics.getWidth() - width, posY);
        }
    }

    public void onCollision(Collidable object) {
        AudioManager.getInstance().reproducirSonido("pong");
    }
}
