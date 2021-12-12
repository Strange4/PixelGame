package game.graphics;

import game.entity.Entity;
import game.graphics.tilemap.TileMap;
import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera {
    private Vector2D position; // the position of the camera in the map
    private final int renderDistanceX;
    private final int renderDistanceY;

    /**
     * A camera object made to follow an entity
     * @param position the position vector to follow
     * @param renderDistanceX the distance width to render
     * @param renderDistanceY the distance height to render
     */
    public Camera(Vector2D position, int renderDistanceX, int renderDistanceY){
        this.position = position;
        this.renderDistanceX = renderDistanceX;
        this.renderDistanceY = renderDistanceY;

    }

    /**
     * A camera object made to follow an entity
     * @param entity the entity to follow
     * @param renderDistanceX the distance width to render
     * @param renderDistanceY the distance height to render
     */
    public Camera(Entity entity, int renderDistanceX, int renderDistanceY){
        this.position = entity.getPosition();
        this.renderDistanceX = renderDistanceX;
        this.renderDistanceY = renderDistanceY;

    }

    /**
     * makes the camera follow a new entity
     * @param entity the new entity to follow
     */
    public void followEntity(Entity entity){
        this.position = entity.getPosition();
    }

    /**
     * sets a new position vector to follow
     * @param position the position vector to follow
     */
    public void setPosition(Vector2D position){
        this.position = position;
    }

    /**
     * renders a tilemap based on the current followed enitity or position
     * @param graphics2D the graphics on which to render
     * @param tileMap the tilemap that is to be rendered
     */
    public void renderMap(Graphics2D graphics2D, TileMap tileMap){
        if(position.getX() < 0 || position.getY() < 0 || position.getX() > tileMap.getMapWidth() || position.getY() > tileMap.getMapHeight()){
            throw new IndexOutOfBoundsException("The camera cannot be out of bounds of the map");
        }
        int mapWidth = tileMap.getMapWidth();
        int mapHeight = tileMap.getMapHeight();
        int startRenderX = (int) position.getX() - renderDistanceX;
        int startRenderY = (int) position.getY() - renderDistanceY;

        // Camera bounds for y
        if(position.getY() - renderDistanceY < 0){
            startRenderY = 0;
        } else if (position.getY() + renderDistanceY > mapHeight){
            startRenderY = mapHeight - (renderDistanceY * 2);
        }

        // camera bounds for x
        if(position.getX() + renderDistanceX > mapWidth){
            startRenderX = mapWidth - (renderDistanceX * 2);
        } else if (position.getX() - renderDistanceX < 0){
            startRenderX = 0;
        }
        BufferedImage mapImage = tileMap.getMapImage(startRenderX, startRenderY,renderDistanceX*2, renderDistanceY*2);
        graphics2D.drawImage(mapImage, 0,0, mapImage.getWidth(), mapImage.getHeight(),null);
    }

}
