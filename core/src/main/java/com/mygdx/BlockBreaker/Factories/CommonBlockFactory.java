package com.mygdx.BlockBreaker.Factories;

import com.mygdx.BlockBreaker.Blocks.Block;
import com.mygdx.BlockBreaker.Blocks.CommonBlock;

public class CommonBlockFactory implements BlockFactory {
    @Override
    public Block createBlock(int x, int y, int width, int height) {
        return new CommonBlock(x, y, width, height);
    }
}

