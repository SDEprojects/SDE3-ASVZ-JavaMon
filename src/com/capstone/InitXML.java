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
    //fields to keep the list of rooms and npc instances referencable
    public Collection<NPCFactory> listOfNPCs = new ArrayList<>();
    public Collection<Room> listOfRooms = new ArrayList<>();

    //basically a getter for the dialog field for the NPC that gets passed to it
    public String npcDialog(String npcName){
        for (NPCFactory NPC : listOfNPCs) {
            if (NPC.getName().toLowerCase().equals(npcName)) {
                return NPC.getDialog();
            }
        }
        return null;
    }

    //getter to return the actual Room object for the room name that gets passed to it
    public Room getRoom(String roomName) {
        for (Room theRoom : listOfRooms) {
            if (theRoom.getName().equals(roomName)) {
                return theRoom;
            }
        }
        return null;
    }

    //initialization method for putting all the NPCs in the XML txt file into the listOfNPCS
    public void initNPCs() {
        try {
            //big formatting block for taking XML from the provided txt doc "NPCs.txt" in data
            File inputFile = new File(String.valueOf(Path.of("data", "NPCs.txt")));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList npcList = doc.getElementsByTagName("npc"); //npcList now contains all the nodes in the file with tag NPC

            for (int temp = 0; temp < npcList.getLength(); temp++) { //iterating over all the nodes in npcList
                Node npc = npcList.item(temp);

                //downcast, getting element we want, then recasting to node, then getting the text and calling the constructor for NPCFactory to add the NPC to the list .... wat... lol
                Element npcEle = (Element) npc;
                String npcName = npcEle.getElementsByTagName("name").item(0).getTextContent();
                String npcDialog = npcEle.getElementsByTagName("dialog").item(0).getTextContent();
                listOfNPCs.add(new NPCFactory(npcName,npcDialog));

            }
        }
        catch (Exception e) {
            System.out.println("there was an error :<");
        }

    }

    //same thing as NPCs, but for Rooms
    public void initRooms() {
        try {
            //big formatting block for taking XML from the provided txt doc "Rooms.txt" in data
            File inputFile = new File(String.valueOf(Path.of("data", "Rooms.txt")));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList roomList = doc.getElementsByTagName("room"); //roomList now contains all the nodes in the file with tag NPC

            for (int temp = 0; temp < roomList.getLength(); temp++) { //iterating over all the nodes in npcList

                Node room = roomList.item(temp);

                //downcast, getting element we want, then recasting to node, then getting the text .... what
                Element roomEle = (Element) room;
                String roomName = roomEle.getElementsByTagName("name").item(0).getTextContent();

                String roomDescription = roomEle.getElementsByTagName("description").item(0).getTextContent();

                //If the string "nothing" is returned from any of the adjacent rooms, that means you cannot navigate to them.
                String roomAdjNorth = roomEle.getElementsByTagName("adjacent_north").item(0).getTextContent();
                String roomAdjSouth = roomEle.getElementsByTagName("adjacent_south").item(0).getTextContent();
                String roomAdjEast = roomEle.getElementsByTagName("adjacent_east").item(0).getTextContent();
                String roomAdjWest = roomEle.getElementsByTagName("adjacent_west").item(0).getTextContent();

                //roomNPC and roomInteractable holds the value from the rooms.txt xml with the npc and interactable tags.
                String roomNPC = roomEle.getElementsByTagName("npc").item(0).getTextContent();
                String roomInteractable = roomEle.getElementsByTagName("interactable").item(0).getTextContent();

                listOfRooms.add(new Room(roomName,roomDescription,roomAdjNorth,roomAdjSouth,roomAdjEast,roomAdjWest, roomNPC, roomInteractable));

            }
        }
        catch (Exception e) {
            System.out.println("there was an error");
        }

    }
}
