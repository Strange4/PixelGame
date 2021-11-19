package game.graphics.tilesv2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class TileReader {
    private Document xmlMap;
    private int tileWidth;
    private int tileHeight;
    private int mapWidth;
    private int mapHeight;
    private ArrayList<TileSet> allTileSets;
    private int[][] tileCoordinates;

    //TODO: render to a bufferedimage in this file using the tilesets and the coordinates

    public TileReader(String path){
        loadXMLFile(path);
        loadTileSets();
        loadLayers();
    }

    private void loadXMLFile(String path){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.xmlMap = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
        } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e) {
            e.printStackTrace();
        }
        Element mapInfo = (Element) this.xmlMap.getElementsByTagName("map").item(0);
        this.tileWidth = Integer.parseInt(mapInfo.getAttribute("tileheight"));
        this.tileHeight = Integer.parseInt(mapInfo.getAttribute("tileheight"));
        this.mapHeight = Integer.parseInt(mapInfo.getAttribute("height"));
        this.mapWidth = Integer.parseInt(mapInfo.getAttribute("width"));
        this.tileCoordinates = new int[mapWidth][mapHeight];
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
            Element image = (Element) tileset.getElementsByTagName("image");
            int imageWidth = Integer.parseInt(image.getAttribute("width"));
            int imageHeight = Integer.parseInt(image.getAttribute("height"));
            String imageSource = image.getAttribute("source");
            allTileSets.add(new TileSet(firstGid, name, tileWidth, tileHeight, nbColumns, imageSource, imageWidth, imageHeight));
        }
    }

    // TODO: do this
    private void loadLayers(){
        NodeList layers = this.xmlMap.getElementsByTagName("layer");
        for(int i=0;i<layers.getLength();i++){
            int[] tileIds = new int[this.mapWidth * this.mapHeight];
            String data = ((Element)layers.item(i)).getElementsByTagName("data").item(0).getTextContent();
            String[] tileNumbers = data.split(",");
            for(int e=0;e<tileNumbers.length;e++){
                tileIds[e] = Integer.parseInt(tileNumbers[e]);
            }
            for(int tileX=0;tileX<this.mapWidth;tileX++){
                for(int tileY=0;tileY<this.mapHeight;tileY++){
                    tileCoordinates[tileX][tileY] = tileIds[tileX + (tileY * mapWidth)];
                }
            }
        }
    }

    //TODO: only render the tiles that are non-zero
    public void render(Graphics2D graphics2D){

    }

}
