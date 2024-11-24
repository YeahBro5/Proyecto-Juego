package com.mygdx.BlockBreaker.Factories;

import com.badlogic.gdx.Gdx;
import com.mygdx.BlockBreaker.Blocks.Block;
import java.util.ArrayList;

public class LevelFactory {

    // Metodo para crear bloques segun el nivel
    public static ArrayList<Block> createLevel(int level) {
        ArrayList<Block> blocks = new ArrayList<>();
        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();

        // Nivel 1
        if (level == 1) {
            // Fábrica para bloques comunes
            BlockFactory factory = new CommonBlockFactory();
            for (int i = 0; i < 2; i++) {
                y -= blockHeight + 10;
                for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                    blocks.add(factory.createBlock(x, y, blockWidth, blockHeight));
                }
            }
        }
        // Nivel 2
        else if (level == 2) {
            // Fábrica para bloques comunes
            BlockFactory factory = new CommonBlockFactory();
            for (int i = 0; i < 3; i++) {
                y -= blockHeight + 10;
                for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                    blocks.add(factory.createBlock(x, y, blockWidth, blockHeight));
                }
            }
        }
        // Nivel 3
        else if (level == 3) {
            for (int i = 0; i < 4; i++) {
                y -= blockHeight + 10;
                for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                    if (x == 165 && y == Gdx.graphics.getHeight() - 144 || x == Gdx.graphics.getWidth() - 235 && y == Gdx.graphics.getHeight() - 144) {
                        // fábrica diferente para los bloques indestructibles
                        BlockFactory indestructibleFactory = new IndestructibleBlockFactory();
                        blocks.add(indestructibleFactory.createBlock(x, y, blockWidth, blockHeight));  // Bloque indestructible
                    } else {
                        // fábrica diferente para los bloques comunes
                        BlockFactory commonFactory = new CommonBlockFactory();
                        blocks.add(commonFactory.createBlock(x, y, blockWidth, blockHeight));  // Bloque común
                    }
                }
            }
        }
        // Nivel 4
        else if (level == 4) {
            for (int i = 0; i < 5; i++) {
                y -= blockHeight + 10;
                for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                    if ((x < 405 || x > 415) && y == Gdx.graphics.getHeight() - 180) {
                        BlockFactory indestructibleFactory = new IndestructibleBlockFactory();
                        blocks.add(indestructibleFactory.createBlock(x, y, blockWidth, blockHeight));  // Bloque indestructible
                    } else {
                        BlockFactory commonFactory = new CommonBlockFactory();
                        blocks.add(commonFactory.createBlock(x, y, blockWidth, blockHeight));  // Bloque común
                    }
                }
            }
        }
        // Niveles adicionales
        else {
            for (int i = 0; i < 2 + level; i++) {
                y -= blockHeight + 10;
                for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                    BlockFactory factory = new CommonBlockFactory();
                    blocks.add(factory.createBlock(x, y, blockWidth, blockHeight));  // Bloque común
                }
            }
        }

        return blocks;
    }
}



