package com.mygdx.BlockBreaker;

public interface Collidable {
    void onCollision(Collidable other);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
}

