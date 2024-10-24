package com.mygdx.BlockBreaker;

public class BlockFactory {
    public static CommonBlock createBlock(int type, int x, int y, int width, int height) {
        switch (type) {
            case 1: return new CommonBlock(x, y, width, height); // Bloque normal
            //case 2: return new StrongBlock(x, y, width, height); // Bloque más fuerte
            // Otros tipos de bloques
        }
        return null;
    }
}
