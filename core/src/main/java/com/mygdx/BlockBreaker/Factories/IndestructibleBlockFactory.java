package com.mygdx.BlockBreaker.Factories;

import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Blocks.IndestructibleBlock;

public class IndestructibleBlockFactory implements BlockFactory {
    @Override
    public Block createBlock(int x, int y, int width, int height) {
        return new IndestructibleBlock(x, y, width, height);
    }
}
