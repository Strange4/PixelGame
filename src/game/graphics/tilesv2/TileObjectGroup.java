package game.graphics.tilesv2;

import java.util.ArrayList;

public class TileObjectGroup {
    private String name;
    private ArrayList<TileMapObject> objects;

    public TileObjectGroup(String name){
        this.name = name;
    }

    public void addObject(TileMapObject object){
        objects.add(object);
    }
}
