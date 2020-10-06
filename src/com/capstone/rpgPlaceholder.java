package com.capstone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.nio.file.Path;

public class rpgPlaceholder {
    public static void main(String[] args) {
        try {
            File inputFile = new File(String.valueOf(Path.of("data", "NPCs.txt")));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.print("Root element: ");
            System.out.println(doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("npc");
            System.out.println("----------------------------");

            Node weiNode = nList.item(0);

            System.out.println(weiNode.getNodeName());

            //downcasting, getting element we want, then recasting to node, then getting the text .... what
            Element weiEle = (Element) weiNode;
            Node firstNameNode = weiEle.getElementsByTagName("dialog").item(0);
            System.out.println(firstNameNode.getTextContent()); // use the string returned to pass as an argument to class constructors for NPC's/Rooms or whatever

        }
        catch (Exception e) {
            System.out.println("there was an error");
        }

    }
}
