package com.mygdx.BlockBreaker.Factories;

import com.badlogic.gdx.Gdx;
import com.mygdx.BlockBreaker.Blocks.Block;
import java.util.ArrayList;

public class LevelFactory {

    // Mtodo para crear bloques según el nivel
    public static ArrayList<Block> createLevel(int level) {
        ArrayList<Block> blocks = new ArrayList<>();
        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();

        // Nivel 1
        if (level == 1) {
            for (int i = 0 ; i < 3 ; i++) {
                y -= blockHeight + 10;
                for (int x = 5 ; x < Gdx.graphics.getWidth() ; x += blockWidth + 10) {
                    blocks.add(BlockFactory.createBlock(1, x, y, blockWidth, blockHeight));
                }
            }
        }
        // Nivel 2
        else if (level == 2) {
            for (int i = 0 ; i < 4 ; i++) {
                y -= blockHeight + 10;
                for (int x = 5 ; x < Gdx.graphics.getWidth() ; x += blockWidth + 10) {
                    blocks.add(BlockFactory.createBlock(1, x, y, blockWidth, blockHeight));
                }
            }
        }
        // Nivel 3
        else if (level == 3) {
            for (int i = 0 ; i < 4 ; i++) {
                y -= blockHeight + 10;
                for (int x = 5 ; x < Gdx.graphics.getWidth() ; x += blockWidth + 10) {
                    if (x == 165 && y == Gdx.graphics.getHeight()-144 ||
                        x == Gdx.graphics.getWidth()-235 && y == Gdx.graphics.getHeight()-144) {
                        blocks.add(BlockFactory.createBlock(2, x, y, blockWidth, blockHeight));
                    } else {
                        blocks.add(BlockFactory.createBlock(1, x, y, blockWidth, blockHeight));
                    }
                }
            }
        }

        else {
            for (int i = 0 ; i < 2+level ; i++) {
                y -= blockHeight + 10;
                for (int x = 5 ; x < Gdx.graphics.getWidth() ; x += blockWidth + 10) {
                    System.out.println(x + " " + y + "\n");
                    blocks.add(BlockFactory.createBlock(1, x, y, blockWidth, blockHeight));
                }
            }
        }

        return blocks;
    }
}
