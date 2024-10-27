package com.mygdx.BlockBreaker.Managers;

import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Paddle;
import com.mygdx.BlockBreaker.PingBall;

import static com.badlogic.gdx.graphics.Color.*;

public class CollisionManager {

    public static void checkCollision(PingBall ball, Paddle paddle) {
        if (collidesWith(ball, paddle)) {
            ball.onCollision(paddle);
        } else {
            ball.setColor(WHITE);
        }
    }

    public static void checkCollision(PingBall ball, Block block) {
        if (collidesWith(ball, block)) {
            ball.handleBlockCollision(block);
            block.onCollision(ball);
        }
    }

    private static boolean collidesWith(PingBall ball, Paddle paddle) {
        boolean intersectX = (paddle.getX() + paddle.getWidth() >= ball.getX() - ball.getSize())
            && (paddle.getX() <= ball.getX() + ball.getSize());
        boolean intersectY = (paddle.getY() + paddle.getHeight() >= ball.getY() - ball.getSize())
            && (paddle.getY() <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }

    private static boolean collidesWith(PingBall ball, Block block) {
        boolean intersectX = (block.getX() + block.getWidth() >= ball.getX() - ball.getSize())
            && (block.getX() <= ball.getX() + ball.getSize());
        boolean intersectY = (block.getY() + block.getHeight() >= ball.getY() - ball.getSize())
            && (block.getY() <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }
}
