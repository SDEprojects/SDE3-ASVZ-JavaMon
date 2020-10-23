package com.capstone;

import java.io.*;

public class PlayerLog {
    private File log = new File("src/com/capstone/log.txt");

    public void updateLog(String action){
        try {
            FileReader fr = new FileReader(log);
            FileWriter fw = new FileWriter(log, true);

            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);

            System.out.println("Trying to write");
            bw.write(action);
            bw.newLine();
            bw.flush();
            bw.close();

//            System.out.println("Trying to read");
//            br.read();
        } catch (FileNotFoundException e){
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
