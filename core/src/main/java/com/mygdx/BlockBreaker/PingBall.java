package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Managers.CollisionManager;

import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.WHITE;

public class PingBall extends GameObject implements Collidable{
    private int size, xSpeed, ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;

    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        setPosicion(x, y);
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        estaQuieto = iniciaQuieto;
    }

    public boolean estaQuieto() { return estaQuieto; }
    public void setEstaQuieto(boolean bb) { estaQuieto = bb; }
    public void setXY(int x, int y) { this.posX = x; this.posY = y; }
    public int getXSpeed() { return xSpeed; }
    public int getYSpeed() { return ySpeed; }
    public void setXSpeed(int speed) { this.xSpeed = speed; }
    public void setYSpeed(int speed) { this.ySpeed = speed; }
    public int getSize() { return size; }
    public void setColor(Color color) { this.color = color; }
    public int getWidth(){return size;}
    public int getHeight(){return size;}

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(posX, posY, size);
    }

    @Override
    public void update() {
        if (estaQuieto) return;
        posX += xSpeed;
        posY += ySpeed;
        if (posX - size < 0 || posX + size > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (posY + size > Gdx.graphics.getHeight()) {
            this.ySpeed = -ySpeed;
        }
    }

    public void checkCollision(Paddle paddle) {
        CollisionManager.checkCollision(this, paddle);
    }

    public void checkCollision(Block block) {
        CollisionManager.checkCollision(this, block);
    }

    public void onCollision(Collidable other) {
        setColor(GREEN);
        if (other instanceof Paddle) {
            handlePaddleCollision((Paddle) other);
        } else if (other instanceof Block) {
            handleBlockCollision((Block) other);
        }
        setColor(WHITE);
    }

    private void handlePaddleCollision(Paddle paddle){
        int directionX = (int) Math.signum(getXSpeed());
        this.ySpeed = -ySpeed;

        float relativeImpact = (float) (getX() - (paddle.getX() + paddle.getWidth() / 2))
            / ((float) paddle.getWidth() / 2) * 1.1f;
        if(getXSpeed() < 5) {
            setXSpeed(getXSpeed() + (int) (5 * relativeImpact));
        } else {
            setXSpeed((int) (directionX * Math.abs(relativeImpact * 9)));
        }
    }

    private void handleBlockCollision(Block block){
        if (lateralCollision(block)) {
            xSpeed = -xSpeed;
            posX = (posX < block.getX()) ? block.getX() - size : block.getX() + block.getWidth() + size;
        } else {
            ySpeed = -ySpeed;
            posY = (posY < block.getY()) ? block.getY() - size : block.getY() + block.getHeight() + size;
        }
    }

    private boolean lateralCollision(Block block) {
        // Calcula las distancias entre el centro de la pelota y cada borde del bloque
        float distLeft = Math.abs(posX - (block.getX() - size));
        float distRight = Math.abs(posX - (block.getX() + block.getWidth() + size));
        float distTop = Math.abs(posY - (block.getY() + block.getHeight() + size));
        float distBottom = Math.abs(posY - (block.getY() - size));

        // La colisión es lateral si las distancias a los lados son menores que las distancias a los bordes superior e inferior
        return distLeft < distTop && distLeft < distBottom || distRight < distTop && distRight < distBottom;
    }
}
