package game.graphics.tiles;

import game.graphics.SpriteSheet;
import game.graphics.tiles.blocks.Block;
import game.graphics.tiles.blocks.NormalBlock;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNorm(String data, SpriteSheet spriteSheet, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        this.blocks = new ArrayList<Block>();

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int blockData = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if(blockData!=0){
//                blocks.add(new NormalBlock());
            }

        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        for(Block block : blocks){
            block.render(graphics2D);
        }
    }
}
