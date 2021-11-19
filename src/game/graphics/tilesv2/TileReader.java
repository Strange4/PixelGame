package game.graphics.tilesv2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    public TileReader(String path){
        loadXMLFile(path);
        loadTileSets();
    }

    private void loadXMLFile(String path){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.xmlMap = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
        } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e) {
            e.printStackTrace();
        }
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

    }

}
