package com.mygdx.BlockBreaker.Managers;

import com.mygdx.BlockBreaker.CommonBlock;
import com.mygdx.BlockBreaker.Paddle;
import com.mygdx.BlockBreaker.PingBall;

import static com.badlogic.gdx.graphics.Color.*;

public class CollisionManager {

    public static void checkCollision(PingBall ball, Paddle paddle) {
        if (collidesWith(ball, paddle)) {
            ball.setColor(GREEN);
            int directionX = (int) Math.signum(ball.getXSpeed());
            ball.reverseYSpeed();

            float relativeImpact = (float) (ball.getX() - (paddle.getX() + paddle.getWidth() / 2))
                / ((float) paddle.getWidth() / 2) * 1.1f;
            if(ball.getXSpeed() < 5) {
                ball.setXSpeed(ball.getXSpeed() + (int) (5 * relativeImpact));
            } else {
                ball.setXSpeed((int) (directionX * Math.abs(relativeImpact * 9)));
            }
        } else {
            ball.setColor(WHITE);
        }
    }

    public static void checkCollision(PingBall ball, CommonBlock block) {
        if (collidesWith(ball, block)) {
            ball.reverseYSpeed();
            block.destroy();
        }
    }

    private static boolean collidesWith(PingBall ball, Paddle paddle) {
        boolean intersectX = (paddle.getX() + paddle.getWidth() >= ball.getX() - ball.getSize())
            && (paddle.getX() <= ball.getX() + ball.getSize());
        boolean intersectY = (paddle.getY() + paddle.getHeight() >= ball.getY() - ball.getSize())
            && (paddle.getY() <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }

    private static boolean collidesWith(PingBall ball, CommonBlock block) {
        boolean intersectX = (block.getX() + block.getWidth() >= ball.getX() - ball.getSize())
            && (block.getX() <= ball.getX() + ball.getSize());
        boolean intersectY = (block.getY() + block.getHeight() >= ball.getY() - ball.getSize())
            && (block.getY() <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }
}
