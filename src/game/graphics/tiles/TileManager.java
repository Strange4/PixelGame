package game.graphics.tiles;

import game.graphics.SpriteSheet;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import org.w3c.dom.Document;
import java.util.ArrayList;

public class TileManager {
    public static ArrayList<TileMap> tilemap;

    public TileManager(){
        tilemap = new ArrayList<TileMap>();
    }

    public TileManager(String path){
        addTileMap(path, 64, 64);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight){
        String imagePath;
        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount; // how many tiles are in the tileset
        int tileColumns;
        int layers = 0;
        SpriteSheet spriteSheet = null;

        String[] data = new String[10]; // the comma separated data in each layer
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // trying to read the xml map
            Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(5);
            Element element = (Element) node;

            // Getting the tileset
            imagePath = ((Element)element.getElementsByTagName("image").item(0)).getAttribute("source");
            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
            tileCount = Integer.parseInt(element.getAttribute("tilecount"));
            tileColumns = Integer.parseInt(element.getAttribute("columns"));
            spriteSheet = new SpriteSheet(imagePath, tileWidth, tileHeight, false, false);

            list = doc.getElementsByTagName("layer");
            layers = list.getLength();
            for(int i=0;i<layers;i++){
                node = list.item(i);
                element = (Element) node;
                if(i==0){
                    width = Integer.parseInt(element.getAttribute("width"));
                    height = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(0).getTextContent();
                System.out.println("---------------------------\n"+data[i]);
                if(i>=1){
                        tilemap.add(new TileMapNorm(data[i], spriteSheet, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tilemap.add(new TileMapObject(data[i], spriteSheet, width, height, blockWidth, blockHeight, tileColumns));
                }
            }

        } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e){
            e.printStackTrace();
        }
    }

    public void render(Graphics2D graphics2D){
        for(TileMap tm : tilemap){
            tm.render(graphics2D);
        }
    }
}
