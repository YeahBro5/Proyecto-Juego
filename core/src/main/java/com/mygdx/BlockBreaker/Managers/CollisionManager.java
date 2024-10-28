package com.mygdx.BlockBreaker.Managers;

import com.mygdx.BlockBreaker.Collidable;

public class CollisionManager {

    public static void checkCollision(Collidable ball, Collidable other) {
        if (collidesWith(ball, other)) {
            ball.onCollision(other);
            other.onCollision(ball);
        }
    }

    private static boolean collidesWith(Collidable ball, Collidable other) {
        boolean intersectX = (other.getX() + other.getWidth() >= ball.getX() - ball.getWidth())
            && (other.getX() <= ball.getX() + ball.getWidth());
        boolean intersectY = (other.getY() + other.getHeight() >= ball.getY() - ball.getHeight())
            && (other.getY() <= ball.getY() + ball.getHeight());
        return intersectX && intersectY;
    }
}
