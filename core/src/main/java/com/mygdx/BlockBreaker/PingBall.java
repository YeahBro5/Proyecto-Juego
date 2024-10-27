package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Blocks.CommonBlock;
import com.mygdx.BlockBreaker.Managers.CollisionManager;

import static com.badlogic.gdx.graphics.Color.GREEN;

public class PingBall implements Collidable{
    private int x, y, size, xSpeed, ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;

    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        estaQuieto = iniciaQuieto;
    }

    public boolean estaQuieto() { return estaQuieto; }
    public void setEstaQuieto(boolean bb) { estaQuieto = bb; }
    public void setXY(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getXSpeed() { return xSpeed; }
    public int getYSpeed() { return ySpeed; }
    public void setXSpeed(int speed) { this.xSpeed = speed; }
    public void setYSpeed(int speed) { this.ySpeed = speed; }
    public int getSize() { return size; }
    public void setColor(Color color) { this.color = color; }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void update() {
        if (estaQuieto) return;
        x += xSpeed;
        y += ySpeed;
        if (x - size < 0 || x + size > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y + size > Gdx.graphics.getHeight()) {
            this.ySpeed = -ySpeed;
        }
    }

    public void onCollision(Object other) {
        if (other instanceof Paddle) {
            handlePaddleCollision((Paddle) other);
        } else if (other instanceof CommonBlock) {
            handleBlockCollision((CommonBlock) other);
        }
    }

    private void handlePaddleCollision(Paddle paddle){
        setColor(GREEN);
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

    public void handleBlockCollision(Block block){
        if (lateralCollision(block)) {
            xSpeed = -xSpeed;
            x = (x < block.getX()) ? block.getX() - size : block.getX() + block.getWidth() + size;
        } else {
            ySpeed = -ySpeed;
            y = (y < block.getY()) ? block.getY() - size : block.getY() + block.getHeight() + size;
        }
    }

    public void checkCollision(Paddle paddle) {
        CollisionManager.checkCollision(this, paddle);
    }

    public void checkCollision(Block block) {
        CollisionManager.checkCollision(this, block);
    }

    private boolean lateralCollision(Block block) {
        // Calcula las distancias entre el centro de la pelota y cada borde del bloque
        float distLeft = Math.abs(x - (block.getX() - size));
        float distRight = Math.abs(x - (block.getX() + block.getWidth() + size));
        float distTop = Math.abs(y - (block.getY() + block.getHeight() + size));
        float distBottom = Math.abs(y - (block.getY() - size));

        // La colisión es lateral si las distancias a los lados son menores que las distancias a los bordes superior e inferior
        return distLeft < distTop && distLeft < distBottom || distRight < distTop && distRight < distBottom;
    }
}
