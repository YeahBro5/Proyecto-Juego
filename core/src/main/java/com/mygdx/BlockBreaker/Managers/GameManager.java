package com.mygdx.BlockBreaker.Managers;

import java.util.ArrayList;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import com.mygdx.BlockBreaker.*;
import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Blocks.IndestructibleBlock;
import com.mygdx.BlockBreaker.Factories.LevelFactory;
import com.mygdx.BlockBreaker.Managers.AudioManager;

public class GameManager {
    private static GameManager instance;
    private PingBall ball;
    private Paddle paddle;
    private ArrayList<Block> blocks;
    private int lives = 3;
    private int score;
    private int level;

    private GameManager() {
        blocks = new ArrayList<>();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void initializeLevel(int level) {
        this.level = level;
        this.ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, 10, 5, 7, true);
        this.paddle = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, 100, 10);
        this.blocks = LevelFactory.createLevel(level); // Usar LevelFactory para crear los bloques
    }

    public void updateGameState() {

        // Si la bola está quieta, verificar si el jugador la lanza
        if (ball.estaQuieto()) {
            ball.setXY(paddle.getX() + paddle.getWidth() / 2 - 5, paddle.getY() + paddle.getHeight() + 11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        } else {
            ball.update();
        }

        // Verificar si la bola cayó al fondo de la pantalla
        if (ball.getY() < 0) {
            lives--;
            AudioManager.getInstance().reproducirSonido("damage");
            ball = new PingBall(paddle.getX() + paddle.getWidth() / 2 - 5, paddle.getY() + paddle.getHeight() + 11, 10, 5, 7, true);
            if (lives <= 0) resetGame();
        }

        // Verificar si se completó el nivel (solo considerando bloques destructibles)
        boolean allDestructibleBlocksDestroyed = blocks.stream()
            .filter(block -> !(block instanceof IndestructibleBlock))  // Excluye bloques indestructibles
            .allMatch(Block::isDestroyed);

        if (allDestructibleBlocksDestroyed) {
            level++;
            initializeLevel(level);
        }
    }

    private void resetGame() {
        lives = 3;
        score = 0;
        level = 1;
        initializeLevel(level);
    }

    public void checkCollisions() {
        // Colisiones entre la bola y el paddle
        ball.checkCollision(paddle);

        // Colisiones entre la bola y los bloques
        for (Block block : blocks) {
            ball.checkCollision(block);
        }

        // Remover bloques destruidos y actualizar el puntaje
        blocks.removeIf(block -> {
            if (block.isDestroyed()) {
                score++;
                return true;
            }
            return false;
        });
    }

    public void drawGameObjects(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);

        // Dibujar paddle, bola y bloques
        paddle.draw(shape);
        ball.draw(shape);
        for (Block block : blocks) {
            block.draw(shape);
        }

        shape.end();
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public void dispose() {
    }
}

