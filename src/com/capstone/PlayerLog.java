package com.capstone;

import java.io.*;

public class PlayerLog {
    private File log = new File("src/com/capstone/log.txt");

    public void readLog(){
        try {
            FileReader fr = new FileReader(log);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e){
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateLog(String action){
        try {
            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(action);
            bw.newLine();
//            bw.flush();
            bw.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
