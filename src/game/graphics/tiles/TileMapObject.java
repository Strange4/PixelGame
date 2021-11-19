package game.graphics.tiles;

import game.graphics.SpriteSheet;
import game.graphics.tiles.blocks.Block;

import java.awt.*;
import java.util.HashMap;

public class TileMapObject extends TileMap {
    public static HashMap<String, Block> blocks;

    public TileMapObject(String data, SpriteSheet spriteSheet, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        Block block;
        // Attach  a string  to a block so we can use it
        blocks = new HashMap<String, Block>();

//        String[] block = data.split(",");
//        for (int i = 0; i < (width * height); i++) {
//            int blockData = Integer.parseInt(blocks[i].replaceAll("\\s+", ""));
//            if(blockData!=0){
//                if(blockData == 172){
//                    block = new HoleBlock();
//                } else {
//                    block = new ObjectBlock();
//                }

                // i % width is the x value
                // i / height is the y value
//                blocks.put((i % width) + "," + (i / height), block);
//            }

//        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for(Block block : blocks.values()){
//            block.render(g);
        }
    }
}
