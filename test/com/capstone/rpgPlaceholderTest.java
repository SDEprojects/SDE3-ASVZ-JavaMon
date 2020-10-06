package com.capstone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class rpgPlaceholderTest {
    public static void main(String[] args) {
        try {
            File inputFile = new File("NPCs.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.print("Root element: ");
            System.out.println(doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("npc");
            System.out.println("----------------------------");

            Node weiNode = nList.item(0);

            System.out.println(weiNode);
        }
        catch (Exception e) {
            System.out.println("there was an error");
        }

    }
}
