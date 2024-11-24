package com.mygdx.BlockBreaker.Factories;

import com.mygdx.BlockBreaker.Blocks.Block;

public interface BlockFactory {
    Block createBlock(int x, int y, int width, int height);
}

