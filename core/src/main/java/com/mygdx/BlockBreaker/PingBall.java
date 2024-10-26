package com.mygdx.BlockBreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private void reverseYSpeed() { this.ySpeed = -ySpeed; }
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
            reverseYSpeed();
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
        reverseYSpeed();

        float relativeImpact = (float) (getX() - (paddle.getX() + paddle.getWidth() / 2))
            / ((float) paddle.getWidth() / 2) * 1.1f;
        if(getXSpeed() < 5) {
            setXSpeed(getXSpeed() + (int) (5 * relativeImpact));
        } else {
            setXSpeed((int) (directionX * Math.abs(relativeImpact * 9)));
        }
    }
    public void handleBlockCollision(Block block){
        reverseYSpeed();
    }
    public void checkCollision(Paddle paddle) {
        CollisionManager.checkCollision(this, paddle);
    }

    public void checkCollision(CommonBlock block) {
        CollisionManager.checkCollision(this, block);
    }
}
