package game.graphics;

import game.entity.Entity;
import game.graphics.tilemap.TileMap;
import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera {
    private Vector2D position; // the position of the camera in the map
    private double acceleration;
    private int renderDistanceX;
    private int renderDistanceY;

    public Camera(Vector2D position, double acceleration, int renderDistanceX, int renderDistanceY){
        this.position = position;
        this.acceleration = acceleration;
        this.renderDistanceX = renderDistanceX;
        this.renderDistanceY = renderDistanceY;

    }
    public Camera(Entity entity, double acceleration, int renderDistanceX, int renderDistanceY){
        this.position = entity.getPosition();
        this.acceleration = acceleration;
        this.renderDistanceX = renderDistanceX;
        this.renderDistanceY = renderDistanceY;

    }

    public void followEntity(Entity entity){
        this.position = entity.getPosition();
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }

    // the camera will not go out of bounds
    public void renderMap(Graphics2D graphics2D, TileMap tileMap, int scale){
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
