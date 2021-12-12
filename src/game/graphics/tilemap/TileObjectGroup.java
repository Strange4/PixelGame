package game.graphics.tilemap;

import java.util.ArrayList;

public class TileObjectGroup {
    private String name;
    private final ArrayList<TileMapObject> objects;

    /**
     * a group of tileObjects
     * @param name the name of the tileObject group based on the name in the tmx file
     */
    public TileObjectGroup(String name){
        this.name = name;
        this.objects = new ArrayList<>();
    }

    public void addObject(TileMapObject object){
        objects.add(object);
    }

    public ArrayList<TileMapObject> getObjects() {
        return objects;
    }
}
