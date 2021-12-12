package game.graphics.tilemap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class TileMap {
    private Document xmlMap;
    private int tileWidth; // in pixels
    private int tileHeight; // in pixels
    private int mapWidth; // in tiles
    private int mapHeight; // in tiles
    private ArrayList<TileSet> allTileSets;
    private BufferedImage levelImage;
    private String currentFolder;
    private ArrayList<TileObjectGroup> objectGroups;

    /**
     * an object representation of a tileMap from Tiled Map Editor
     * @param path the path of the tmx file
     */
    public TileMap(String path){
        loadXMLFile(path);
        loadTileSets();
        loadObjects();
        buildLevelImage();
    }

    private void loadObjects(){
        this.objectGroups = new ArrayList<>();
        NodeList objectGroups = this.xmlMap.getElementsByTagName("objectgroup");
        for (int group = 0; group < objectGroups.getLength(); group++) {
            TileObjectGroup tog = new TileObjectGroup(((Element)objectGroups.item(group)).getAttribute("name"));
            NodeList objects = ((Element) objectGroups.item(group)).getElementsByTagName("object");
            for (int obj = 0; obj < objects.getLength(); obj++) {
                Element object = (Element) objects.item(obj);
                TileMapObject tmo = new TileMapObject(Double.parseDouble(object.getAttribute("x")), Double.parseDouble(object.getAttribute("y")), Double.parseDouble(object.getAttribute("width")), Double.parseDouble(object.getAttribute("height")));
                tog.addObject(tmo);
            }
            this.objectGroups.add(tog);
        }
    }

    private void loadXMLFile(String path){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            URL url = Thread.currentThread().getContextClassLoader().getResource(path);
            if(url == null){
                throw new NullPointerException("the path given for the tilemap cannot be found");
            }
            File file = new File(url.toURI());
            this.currentFolder = file.getParentFile().getName();
            this.xmlMap = builder.parse(file);
        } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e) {
            e.printStackTrace();
        }
        Element mapInfo = (Element) this.xmlMap.getElementsByTagName("map").item(0);
        this.tileWidth = Integer.parseInt(mapInfo.getAttribute("tileheight"));
        this.tileHeight = Integer.parseInt(mapInfo.getAttribute("tileheight"));
        this.mapHeight = Integer.parseInt(mapInfo.getAttribute("height"));
        this.mapWidth = Integer.parseInt(mapInfo.getAttribute("width"));
    }

    private void loadTileSets(){
        allTileSets = new ArrayList<>();
        NodeList tilesets = this.xmlMap.getElementsByTagName("tileset");
        for(int i=0;i<tilesets.getLength();i++){
            Element tileset = (Element) tilesets.item(i);
            String name = tileset.getAttribute("name");
            int firstGid = Integer.parseInt(tileset.getAttribute("firstgid"));
            int tileWidth = Integer.parseInt(tileset.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(tileset.getAttribute("tileheight"));
            int nbColumns = Integer.parseInt(tileset.getAttribute("columns"));

            // getting the image
            Element image = (Element) tileset.getElementsByTagName("image").item(0);
            int imageWidth = Integer.parseInt(image.getAttribute("width"));
            int imageHeight = Integer.parseInt(image.getAttribute("height"));
            String imageSource = image.getAttribute("source");
            allTileSets.add(new TileSet(firstGid, name, tileWidth, tileHeight, nbColumns, this.currentFolder + "/" +imageSource, imageWidth, imageHeight));
        }
    }

    private void buildLevelImage(){
        this.levelImage = new BufferedImage(tileWidth * mapWidth, tileHeight * mapHeight, BufferedImage.TYPE_INT_ARGB);
        NodeList layers = this.xmlMap.getElementsByTagName("layer");
        for(int i=0;i<layers.getLength();i++){
            // getting tile tileID's
            int[] tileIds = new int[this.mapWidth * this.mapHeight];
            String data = ((Element)layers.item(i)).getElementsByTagName("data").item(0).getTextContent();
            String[] tileNumbers = data.split(",");
            for(int e=0;e<tileNumbers.length;e++){
                int gid = Integer.parseInt(tileNumbers[e].replaceAll("\\s+", ""));
                if(gid > 0){
                    tileIds[e] = gid;
                }
            }

            // setting the tileId's to a coordinate on the layer
            int[][] tileCoordinates = new int[this.mapWidth][this.mapHeight];
            for(int tileX=0;tileX<this.mapWidth;tileX++){
                for(int tileY=0;tileY<this.mapHeight;tileY++){
                    tileCoordinates[tileX][tileY] = tileIds[tileX + (tileY * mapWidth)];
                }
            }
            // drawing the tileimage into the level image
            for (int tileX = 0; tileX < this.mapWidth; tileX++) {
                for (int tileY = 0; tileY < this.mapHeight; tileY++) {
                    int tid = tileCoordinates[tileX][tileY];
                    if(tid!=0){
                        // find the current tileset
                        TileSet currentTileSet = findTileSet(tid);

                        // drawing the tile
                        int posX = tileX * this.tileWidth;
                        int posY = tileY * this.tileHeight;
                        BufferedImage tileImage = currentTileSet.getTileImage(tid);

                        this.levelImage.getGraphics().drawImage(tileImage, posX, posY, this.tileWidth, this.tileHeight, null);
                    }
                }
            }

        }
    }

    private TileSet findTileSet(int tileId){
        for (TileSet tileset : allTileSets) {
            if (tileId >= tileset.getFirstGid() && tileId < tileset.getLastGid()) {
                return tileset;
            }
        }
        throw new IllegalArgumentException("the tile with tid: "+tileId +" was not found");
    }

    /**
     * renders the map of a graphics2d object
     * @param graphics2D the graphics to be rendered on
     * @param posX the x position on which to render the graphics
     * @param posY the y position on which to render the graphics
     * @param mapPosX the x position of the map that is rendered
     * @param mapPosY the y position of the map that is rendered
     * @param renderDistanceX the width of the render distance
     * @param renderDistanceY the height of the render distance
     */
    public void render(Graphics2D graphics2D, int posX, int posY, int mapPosX, int mapPosY, int renderDistanceX, int renderDistanceY) {
        if(mapPosX - renderDistanceX > this.mapWidth * this.tileWidth){
            throw new IllegalArgumentException("the render distance x: "+renderDistanceX+", at mapPosX: "+ mapPosX+", is too small to see the map");
        }
        if(mapPosY - renderDistanceY > this.mapHeight * this.tileHeight) {
            throw new IllegalArgumentException("the render distance y: " + renderDistanceY + ", at mapPosY: " + mapPosY + ", is too small to see the map");
        }
        // if the renderDistance is outside the map, render until the end of it
        int renderFromX = Math.max(mapPosX - renderDistanceX, 0);
        int renderFromY = Math.max(mapPosY - renderDistanceY, 0);
        int renderWidth = Math.min(renderDistanceX * 2, (this.mapWidth * this.tileWidth) - renderFromX);
        int renderHeight = Math.min(renderDistanceY * 2, (this.mapHeight * this.tileHeight) - renderFromY);
        graphics2D.drawImage(this.levelImage.getSubimage(renderFromX, renderFromY, renderWidth, renderHeight),posX-renderDistanceX, posY-renderDistanceY, renderWidth, renderHeight, null);
    }

    /**
     * gets a part of the map as an image
     * @param mapPosX the x position of the map that is rendered
     * @param mapPosY the y position of the map that is rendered
     * @param width the width of the image taken
     * @param height the height of the image taken
     * @return the image of that part of the map
     */
    public BufferedImage getMapImage(int mapPosX, int mapPosY, int width, int height){
        return this.levelImage.getSubimage(mapPosX, mapPosY, width, height);
    }

    public int getMapWidth(){
        return this.mapWidth * tileWidth;
    }
    public int getMapHeight(){
        return this.mapHeight * tileHeight;
    }

    public ArrayList<TileObjectGroup> getObjectGroups() {
        return objectGroups;
    }
}
