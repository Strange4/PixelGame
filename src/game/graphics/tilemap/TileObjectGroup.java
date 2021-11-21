package game.graphics.tilemap;

import java.util.ArrayList;

public class TileObjectGroup {
    private String name;
    private ArrayList<TileMapObject> objects;

    public TileObjectGroup(String name){
        this.name = name;
        this.objects = new ArrayList<>();
    }

    public void addObject(TileMapObject object){
        objects.add(object);
    }
}
