package com.mygdx.BlockBreaker.Factories;

import com.mygdx.BlockBreaker.Blocks.*;

public class BlockFactory {
    public static Block createBlock(int type, int x, int y, int width, int height) {

        return switch (type) {
            // Bloque normal
            case 1 -> new CommonBlock(x, y, width, height);
            // Bloque indestructible
            case 2 -> new IndestructibleBlock(x, y, width, height);
            // Bloque más fuerte
            //case 3: return new StrongBlock(x, y, width, height);
            // Otros tipos de bloques
            default -> new CommonBlock(x, y, width, height);
        };
    }
}
