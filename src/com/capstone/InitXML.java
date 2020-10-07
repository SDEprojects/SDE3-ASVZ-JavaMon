package com.capstone;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class InitXML {
    public Collection<NPCFactory> listOfNpcs = new ArrayList<>();
    public Collection<Room> listOfRooms = new ArrayList<>();

    public String npcDialog(String npcName){
        for (NPCFactory NPC : listOfNpcs) {
            if (NPC.getName().equals(npcName)) {
                return NPC.getDialog();
            }
        }
        return null;
    }
    public Room getRoom(String roomName) {
        for (Room theRoom : listOfRooms) {
            if (theRoom.getName().equals(roomName)) {
                return theRoom;
            }
        }
        return null;
    }


    public void initNPCs() {
        try {

            //big formating block for taking XML from the provided txt doc "NPCs.txt" in data
            File inputFile = new File(String.valueOf(Path.of("data", "NPCs.txt")));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList npcList = doc.getElementsByTagName("npc"); //npcList now contains all the nodes in the file with tag NPC

            for (int temp = 0; temp < npcList.getLength(); temp++) { //iterating over all the nodes in npcList

                Node npc = npcList.item(temp);

                //downcasting, getting element we want, then recasting to node, then getting the text .... what
                Element npcEle = (Element) npc;
                String npcName = npcEle.getElementsByTagName("name").item(0).getTextContent();
                String npcDialog = npcEle.getElementsByTagName("dialog").item(0).getTextContent();
                listOfNpcs.add(new NPCFactory(npcName,npcDialog));

            }
        }
        catch (Exception e) {
            System.out.println("there was an error");
        }

    }
    public void initRooms() {
        try {

            //big formating block for taking XML from the provided txt doc "NPCs.txt" in data
            File inputFile = new File(String.valueOf(Path.of("data", "Rooms.txt")));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList roomList = doc.getElementsByTagName("room"); //npcList now contains all the nodes in the file with tag NPC

            for (int temp = 0; temp < roomList.getLength(); temp++) { //iterating over all the nodes in npcList

                Node room = roomList.item(temp);

                //downcasting, getting element we want, then recasting to node, then getting the text .... what
                Element roomEle = (Element) room;
                String roomName = roomEle.getElementsByTagName("name").item(0).getTextContent();
                String roomDescription = roomEle.getElementsByTagName("description").item(0).getTextContent();
                String roomAdjNorth = roomEle.getElementsByTagName("adjacent_north").item(0).getTextContent();
                String roomAdjSouth = roomEle.getElementsByTagName("adjacent_south").item(0).getTextContent();
                String roomAdjEast = roomEle.getElementsByTagName("adjacent_east").item(0).getTextContent();
                String roomAdjWest = roomEle.getElementsByTagName("adjacent_west").item(0).getTextContent();

                listOfRooms.add(new Room(roomName,roomDescription,roomAdjNorth,roomAdjSouth,roomAdjEast,roomAdjWest));

            }
        }
        catch (Exception e) {
            System.out.println("there was an error");
        }

    }
}
